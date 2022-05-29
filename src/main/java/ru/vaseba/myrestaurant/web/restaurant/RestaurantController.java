package ru.vaseba.myrestaurant.web.restaurant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vaseba.myrestaurant.entity.Restaurant;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;
import ru.vaseba.myrestaurant.to.RestaurantWithMenu;
import ru.vaseba.myrestaurant.util.RestaurantUtil;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class RestaurantController {
    static final String REST_URL = "/api/restaurants";

    private final RestaurantRepository repository;

    @GetMapping
    public List<Restaurant> getAllEnabled() {
        log.info("getAllEnabled");
        return repository.getAllEnabled();
    }

    @GetMapping("/menu_today")
    public List<RestaurantWithMenu> getWithMenuForToday() {
        log.info("getWithMenuForToday");
        List<Restaurant> restaurants = repository.getWithMenuByDate(LocalDate.now());
        return RestaurantUtil.withMenu(restaurants);
    }

    @GetMapping("/{id}/menu_today")
    public RestaurantWithMenu getWithMenuByRestaurantForToday(@PathVariable int id) {
        log.info("getWithMenuByRestaurantForToday {}", id);
        repository.checkAvailable(id);
        Restaurant restaurant = repository.getWithMenuByRestaurantAndDate(id, LocalDate.now());
        return RestaurantUtil.withMenu(restaurant);
    }
}