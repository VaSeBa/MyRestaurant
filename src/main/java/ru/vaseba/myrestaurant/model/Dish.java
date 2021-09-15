package ru.vaseba.myrestaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import ru.vaseba.myrestaurant.meta.Meta;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Meta.Dish.TABLE_NAME)
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"menu", "price"})
@ToString(callSuper = true)
public class Dish extends BaseNamedEntity {

    @ManyToOne
    @JoinColumn(name = Meta.Dish.MENU_COLUMN, nullable = false)
    private Menu menu;

    @NotNull
    @Range(min = 0)
    // Стоимость блюда в копейках
    @Column(name = Meta.Dish.PRICE)
    private Long price;

}
