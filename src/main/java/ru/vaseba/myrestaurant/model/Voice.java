package ru.vaseba.myrestaurant.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "voice", uniqueConstraints =
        {@UniqueConstraint(columnNames =
                {"user_id", "restaurants_id"}, name = "voice_unique_user_restaurant_id_idx")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Voice extends BaseEntity {

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurants_id", nullable = false)
    private Restaurant restaurant;

    public Voice(Integer id, LocalDate date, User user, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }
}
