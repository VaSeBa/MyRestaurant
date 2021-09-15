package ru.vaseba.myrestaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vaseba.myrestaurant.meta.Meta;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = Meta.Menu.TABLE_NAME)
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"restaurant", "actual", "dishes"})
@ToString(callSuper = true, exclude = {"dishes"})
public class Menu extends BaseNamedEntity {

    @ManyToOne
    @JoinColumn(name = Meta.Menu.RESTAURANT_COLUMN, nullable = false)
    private Restaurant restaurant;

    @NotNull
    @Column(name = Meta.Menu.ACTUAL)
    private Boolean actual;

    @OneToMany(mappedBy = "menu", fetch = LAZY, cascade = {PERSIST, MERGE, REFRESH})
    @OrderBy("name")
    private List<Dish> dishes;

}
