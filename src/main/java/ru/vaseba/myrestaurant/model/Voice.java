package ru.vaseba.myrestaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vaseba.myrestaurant.meta.Meta;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = Meta.Voice.TABLE_NAME,
        uniqueConstraints = {@UniqueConstraint(columnNames = {Meta.Voice.USER_COLUMN, Meta.Voice.DATE})}
)
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"user", "date", "menu"})
@ToString(callSuper = true)
public class Voice extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = Meta.Voice.USER_COLUMN, updatable = false, nullable = false)
    private User user;

    @Column(name = Meta.Voice.DATE, insertable = false, updatable = false, nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = Meta.Voice.MENU_COLUMN, nullable = false)
    private Menu menu;

}
