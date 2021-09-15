package ru.vaseba.myrestaurant.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vaseba.myrestaurant.model.Dish;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishDto extends BaseNamedDto<Dish> {

    private UUID menuId;
    private Long price;

    public DishDto() {
        super();
    }

    public DishDto(Dish entity) {
        super(entity);
        menuId = entity.getMenu().getId();
        price = entity.getPrice();
    }

}
