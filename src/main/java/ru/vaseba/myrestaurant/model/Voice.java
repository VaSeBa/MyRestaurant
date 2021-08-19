package ru.vaseba.myrestaurant.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "voice")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
public class Voice extends AbstractPersistable<Integer> {

    @NotNull
    @Column(name = "time_of_voicing", nullable = false)
    private LocalDate timeOfVoicing;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurants_id", nullable = false)
    private Restaurant restaurant;


}
