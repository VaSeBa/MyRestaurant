package ru.vaseba.myrestaurant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@Slf4j
@Tag(name = "Dish Controller")
public class DishController {
    static final String GET_ALL = "/api/dishes";
    static final String CREATE = "/api/dishes";
    static final String GET_DISH_BY_ID = "/api/dishes/{dish_id}";

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
        log.info("get all dishes: {}", dishes);
        return dishes;
    }

    @GetMapping(GET_DISH_BY_ID)
    public Optional<Dish> getById(@PathVariable("dish_id") int dishId) {
        Optional<Dish> dish = dishRepository.findById(dishId);
        log.info("get Dish by id: {}, dish: {}", dishId, dish);
        return dish;
    }

    @PostMapping(value = DishController.CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Dish> createMenu(@RequestBody Dish dish) {
        Dish created = dishRepository.save(dish);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CREATE + "/{dish_id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @DeleteMapping("/{dish_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int dish_id) {
        dishRepository.deleteById(dish_id);
    }
}
