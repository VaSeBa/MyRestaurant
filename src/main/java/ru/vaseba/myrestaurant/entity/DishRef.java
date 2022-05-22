package ru.vaseba.myrestaurant.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dish_ref", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "uk_dish_ref")})
@Getter
@Setter
@NoArgsConstructor
public class DishRef extends NamedEntity {

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    public DishRef(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }
}
