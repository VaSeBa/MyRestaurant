package ru.vaseba.myrestaurant.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "voice", uniqueConstraints =
        {@UniqueConstraint(columnNames =
                {"user_id", "time_of_voicing"}, name = "voice_idx")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Voice extends BaseEntity {

    @NotNull
    @Column(name = "time_of_voicing", nullable = false)
    private LocalDate timeOfVoicing;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurants_id", nullable = false)
    private Restaurant restaurant;

    public Voice(Integer id, LocalDate timeOfVoicing, User user, Restaurant restaurant) {
        super(id);
        this.timeOfVoicing = timeOfVoicing;
        this.user = user;
        this.restaurant = restaurant;
    }

}
