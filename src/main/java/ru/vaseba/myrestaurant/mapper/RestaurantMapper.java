package ru.vaseba.myrestaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vaseba.myrestaurant.entity.DishRef;
import ru.vaseba.myrestaurant.entity.MenuItem;
import ru.vaseba.myrestaurant.entity.Restaurant;
import ru.vaseba.myrestaurant.to.RestaurantWithMenu;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends BaseMapper<Restaurant, RestaurantWithMenu> {

    @Mapping(target = "dishRefs", expression = "java(getDishRefs(restaurant))")
    @Override
    RestaurantWithMenu toTo(Restaurant restaurant);

    default List<DishRef> getDishRefs(Restaurant restaurant) {
        return restaurant.getMenuItems().stream()
                .map(MenuItem::getDishRef).toList();
    }
}
