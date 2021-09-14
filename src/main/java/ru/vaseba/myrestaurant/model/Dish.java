package ru.vaseba.myrestaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Entity
@Table(name = "dish")
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"menu", "price"})
public class Dish extends BaseNamedEntity {

    @ManyToOne
    @JoinColumn(name = "menu_uuid", nullable = false)
    private Menu menu;

    @Null
    @Range(min = 0)
    // Стоимость блюда в копейках
    @Column(name = "price")
    private Long price;

}
