package ru.vaseba.myrestaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vaseba.myrestaurant.model.DishRef;
import ru.vaseba.myrestaurant.model.MenuItem;
import ru.vaseba.myrestaurant.model.Restaurant;
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
