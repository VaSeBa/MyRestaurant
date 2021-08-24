package ru.vaseba.myrestaurant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vaseba.myrestaurant.model.Restaurant;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;

@RestController
@RequestMapping(RestaurantController.URL)
@Slf4j
@Tag(name = "Restaurant Controller")
public class RestaurantController {
    static final String URL = "/api/restaurants";

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }




}
