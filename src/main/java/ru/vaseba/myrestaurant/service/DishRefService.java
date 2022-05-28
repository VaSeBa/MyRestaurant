package ru.vaseba.myrestaurant.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.entity.DishRef;
import ru.vaseba.myrestaurant.repository.DishRefRepository;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;

@Service
@AllArgsConstructor
public class DishRefService {
    private final RestaurantRepository restaurantRepository;
    private final DishRefRepository dishRefRepository;

    @Transactional
    public DishRef save(int restaurantId, DishRef dishRef) {
        dishRef.setRestaurant(restaurantRepository.getById(restaurantId));
        return dishRefRepository.save(dishRef);
    }
}
