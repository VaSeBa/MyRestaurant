package ru.vaseba.myrestaurant.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vaseba.myrestaurant.model.Dish;
import ru.vaseba.myrestaurant.model.Restaurant;

import java.util.List;

import static java.util.stream.Collectors.toList;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantTo extends NamedTo {

    private String name;
    private List<Dish> menus = List.of();

    public RestaurantTo(Integer id, String name) {
        super(id, name);
    }

    public RestaurantTo(Integer id, String name, String name1, List<Dish> menus) {
        super(id, name);
        this.name = name1;
        this.menus = menus;
    }

    public RestaurantTo(Restaurant restaurant) {
        super();
    }
}
