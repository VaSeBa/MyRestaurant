package ru.vaseba.myrestaurant.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vaseba.myrestaurant.model.Restaurant;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantDto extends BaseNamedDto<Restaurant> {

    private String email;
    private String address;
    private List<MenuDto> menus;

    public RestaurantDto() {
        super();
    }

    public RestaurantDto(Restaurant entity) {
        super(entity);
        email = entity.getEmail();
        address = entity.getAddress();
        menus = entity.getMenus().stream().map(MenuDto::new).collect(toList());
    }

}
