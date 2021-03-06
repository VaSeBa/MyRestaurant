package ru.vaseba.myrestaurant.web.restaurant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vaseba.myrestaurant.model.Restaurant;
import ru.vaseba.myrestaurant.mapper.RestaurantMapper;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;
import ru.vaseba.myrestaurant.to.RestaurantWithMenu;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class RestaurantController {
    static final String REST_URL = "/api/restaurants";

    private final RestaurantRepository repository;
    private final RestaurantMapper restaurantMapper;

    @GetMapping
    @Cacheable("restaurants")
    public List<Restaurant> getAllEnabled() {
        log.info("getAllEnabled");
        return repository.getAllEnabled();
    }

    @GetMapping("/menu_today")
    @Cacheable(value = "allRestaurantsWithMenu", keyGenerator = "currentDateKeyGenerator")
    public List<RestaurantWithMenu> getWithMenuForToday() {
        log.info("getWithMenuForToday");
        List<Restaurant> restaurants = repository.getWithMenuByDate(LocalDate.now());
        return restaurantMapper.toToList(restaurants);
    }

    @GetMapping("/{id}/menu_today")
    @Cacheable(value = "restaurantWithMenu", keyGenerator = "currentDateKeyGenerator")
    public RestaurantWithMenu getWithMenuByRestaurantForToday(@PathVariable int id) {
        log.info("getWithMenuByRestaurantForToday {}", id);
        repository.checkAvailable(id);
        Restaurant restaurant = repository.getWithMenuByRestaurantAndDate(id, LocalDate.now());
        return restaurantMapper.toTo(restaurant);
    }
}