package ru.vaseba.myrestaurant.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.vaseba.myrestaurant.model.Menu;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuDto extends BaseNamedDto<Menu> {

    private UUID restaurantId;
    @ApiModelProperty(position = 4)
    private Boolean actual;
    @ApiModelProperty(position = 4)
    private List<DishDto> dishes = List.of();

    public MenuDto() {
        super();
    }

    public MenuDto(Menu entity) {
        super(entity);
        restaurantId = entity.getRestaurant().getId();
        actual = entity.getActual();
        dishes = entity.getDishes().stream().map(DishDto::new).collect(toList());
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    @ApiModelProperty(position = 3, hidden = true)
    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

}
