package ru.vaseba.myrestaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends BaseEntity {

    @Column(name = "name")
    @Size(max = 128)
    private String name;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
//    @JsonManagedReference(value = "restaurant-menu")
//    private List<Dish> menu;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
//    @JsonManagedReference(value = "restaurant-voices")
//    private List<Voice> voices;

}
