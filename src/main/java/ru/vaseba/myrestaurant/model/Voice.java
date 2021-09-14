package ru.vaseba.myrestaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "voice", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_uuid", "date"})})
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"user", "date", "menu"})
public class Voice extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "menu_uuid")
    private Menu menu;

}
