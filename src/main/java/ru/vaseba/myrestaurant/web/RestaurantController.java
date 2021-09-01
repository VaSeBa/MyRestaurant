package ru.vaseba.myrestaurant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vaseba.myrestaurant.model.Restaurant;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static ru.vaseba.myrestaurant.util.ValidationUtil.checkNew;
import static ru.vaseba.myrestaurant.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = RestaurantController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Restaurant Controller")
public class RestaurantController {
    static final String URL = "/api/restaurants";

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        log.info("get all restaurants: {}", restaurants);
        return restaurants;
    }

    @GetMapping(value = "/{id}")
    public Optional<Restaurant> getById(@PathVariable int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        log.info("get restaurant by id: {}, restaurant: {}", id, restaurant);
        return restaurant;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        Restaurant created = restaurantRepository.save(restaurant);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @DeleteMapping( "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        checkNotFoundWithId(id != 0, id);
    }


}
