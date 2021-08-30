package ru.vaseba.myrestaurant.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishTo extends BaseTo {
    private BigDecimal price;
    private Integer restaurantId;
}
