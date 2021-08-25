package ru.vaseba.myrestaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
public class Restaurant extends NamedEntity {

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant", cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "restaurant-menu")
//    private List<Dish> menu = List.of();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
//    @JsonManagedReference(value = "restaurant-voices")
//    private List<Voice> voices;

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
