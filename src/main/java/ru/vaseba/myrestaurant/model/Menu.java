package ru.vaseba.myrestaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "menu")
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"restaurant", "actual"})
public class Menu extends BaseNamedEntity {

    @ManyToOne
    @JoinColumn(name = "restaurant_uuid", nullable = false)
    private Restaurant restaurant;

    @NotNull
    @Column(name = "actual")
    private Boolean actual;

}
