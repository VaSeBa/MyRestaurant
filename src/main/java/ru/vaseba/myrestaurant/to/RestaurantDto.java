package ru.vaseba.myrestaurant.to;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(position = 2, required = true, example = "New address")
    private String address;
    @ApiModelProperty(position = 3, required = true, example = "new@foodvoice.ru")
    private String email;
    @ApiModelProperty(position = 4)
    private List<MenuDto> menus = List.of();

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
