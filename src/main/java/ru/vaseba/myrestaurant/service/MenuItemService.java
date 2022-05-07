package ru.vaseba.myrestaurant.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.entity.MenuItem;
import ru.vaseba.myrestaurant.repository.MenuItemRepository;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;


@Service
@AllArgsConstructor
public class MenuItemService {
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @Transactional
    public MenuItem save(int restaurantId, MenuItem menuItem) {
        menuItem.setRestaurant(restaurantRepository.getById(restaurantId));
        return menuItemRepository.save(menuItem);
    }
}
