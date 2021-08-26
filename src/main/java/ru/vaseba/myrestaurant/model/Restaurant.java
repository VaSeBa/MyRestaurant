package ru.vaseba.myrestaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "unique_restaurant_name_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Restaurant extends NamedEntity {

    @JsonManagedReference(value = "restaurant")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    private List<Dish> menu;

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
