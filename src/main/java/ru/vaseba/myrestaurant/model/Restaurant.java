package ru.vaseba.myrestaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends AbstractPersistable<Integer> {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @JsonManagedReference(value = "restaurant-menu")
    private List<Dish> menu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @JsonManagedReference(value = "restaurant-voices")
    private List<Voice> voices;

}
