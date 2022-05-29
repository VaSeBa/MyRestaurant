package ru.vaseba.myrestaurant.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import ru.vaseba.myrestaurant.entity.DishRef;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantWithMenu extends NamedTo {

    String address;
    List<DishRef> dishRefs;

    public RestaurantWithMenu(Integer id, String name, String address, List<DishRef> dishRefs) {
        super(id, name);
        this.address = address;
        this.dishRefs = dishRefs;
    }
}
