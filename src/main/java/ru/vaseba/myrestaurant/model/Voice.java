package ru.vaseba.myrestaurant.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "voice", uniqueConstraints =
        {@UniqueConstraint(columnNames =
                {"user_id", "date", "time"}, name = "voice_idx")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Voice extends BaseEntity {

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "time", nullable = false)
    private LocalTime time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurants_id", nullable = false)
    private Restaurant restaurant;

    public Voice(Integer id, LocalDate date, LocalTime time, User user, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.time = time;
        this.user = user;
        this.restaurant = restaurant;
    }

}
