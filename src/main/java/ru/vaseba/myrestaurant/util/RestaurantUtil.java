package ru.vaseba.myrestaurant.util;

import lombok.experimental.UtilityClass;
import ru.vaseba.myrestaurant.entity.DishRef;
import ru.vaseba.myrestaurant.entity.MenuItem;
import ru.vaseba.myrestaurant.entity.Restaurant;
import ru.vaseba.myrestaurant.to.RestaurantWithMenu;

import java.util.List;

@UtilityClass
public class RestaurantUtil {

    public static RestaurantWithMenu withMenu(Restaurant restaurant) {
        List<DishRef> dishRefs = restaurant.getMenuItems().stream().map(MenuItem::getDishRef).toList();
        return new RestaurantWithMenu(restaurant.id(), restaurant.getName(), restaurant.getAddress(), dishRefs);
    }

    public static List<RestaurantWithMenu> withMenu(List<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantUtil::withMenu).toList();
    }
}
