package ru.vaseba.myrestaurant.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
public class Dish extends BaseEntity {

    // https://stackoverflow.com/questions/8148684/what-data-type-to-use-for-money-in-java/43051227#43051227
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @JoinColumn(name = "restaurants_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

}
