package ru.vaseba.myrestaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurants_id", "name"}, name = "dish_name_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends NamedEntity {

    // https://stackoverflow.com/questions/8148684/what-data-type-to-use-for-money-in-java/43051227#43051227
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @JsonBackReference(value = "restaurant")
    @JoinColumn(name = "restaurants_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public Dish(Integer id, String name, BigDecimal price, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
