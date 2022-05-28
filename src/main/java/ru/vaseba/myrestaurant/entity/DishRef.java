package ru.vaseba.myrestaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "dish_ref", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id"}, name = "uk_dish_ref")})
@Getter
@Setter
@NoArgsConstructor
public class DishRef extends NamedEntity {

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant restaurant;

    public DishRef(Integer id, String name, int price, Restaurant restaurant ) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
    }
}
