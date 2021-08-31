package ru.vaseba.myrestaurant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vaseba.myrestaurant.model.Dish;
import ru.vaseba.myrestaurant.repository.DishRepository;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static ru.vaseba.myrestaurant.util.ValidationUtil.checkNew;
import static ru.vaseba.myrestaurant.util.ValidationUtil.checkNotFoundWithId;

@RestController
//@RequestMapping(value = DishController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Dish Controller")
public class DishController {
    static final String GET_ALL = "/api/dishes";
    static final String GET_ALL_BY_RESTAURANT = "/api/admin/restaurants/{restaurantsId}/dishes";
    static final String GET_DISH_BY_ID = "/api/dish/{dish_id}";

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DishController(DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping(value = DishController.GET_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll() {
        List<Dish> dishes = dishRepository.findAll();
        log.info("get all users: {}", dishes);
        return dishes;
    }

    @GetMapping(value = DishController.GET_ALL_BY_RESTAURANT, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(@PathVariable int restaurantsId) {
        List<Dish> dishes = dishRepository.getAllByRestaurant(restaurantsId);
        log.info("get all users: {}", dishes);
        return dishes;
    }

    @GetMapping(GET_DISH_BY_ID)
    public Optional<Dish> getById(@PathVariable("dish_id") int dishId) {
        Optional<Dish> dish = dishRepository.findById(dishId);
        log.info("get Dish by id: {}, user: {}", dishId, dish);
        return dish;
    }

    @PostMapping(value = DishController.GET_ALL_BY_RESTAURANT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createMenu(@PathVariable int restaurantsId, @RequestBody Dish dish) {
        Dish created = addDishInRestaurantMenu(dish, restaurantsId);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(GET_ALL_BY_RESTAURANT + "/{dish_id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @DeleteMapping("/{dish_id}")
    public void delete(@PathVariable int dish_id) {
        checkNotFoundWithId(dish_id != 0, dish_id);
    }


    //ToDo: come up with where you can move instead of the service
    public Dish addDishInRestaurantMenu(Dish dish, int restaurantId) {
        checkNew(dish);
        dish.setRestaurant(restaurantRepository.getById(restaurantId));
        return dishRepository.save(dish);
    }
}
