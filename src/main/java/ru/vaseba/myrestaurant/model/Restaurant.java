package ru.vaseba.myrestaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vaseba.myrestaurant.meta.Meta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = Meta.Restaurant.TABLE_NAME,
        uniqueConstraints = {@UniqueConstraint(columnNames = {Meta.Restaurant.NAME, Meta.Restaurant.ADDRESS})}
)
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"email", "address", "menus"})
@ToString(callSuper = true, exclude = "menus")
public class Restaurant extends BaseNamedEntity {

    @Email
    @NotBlank
    @Size(min = 3, max = 254)
    // https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address
    @Column(name = Meta.Restaurant.EMAIL, nullable = false)
    private String email;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = Meta.Restaurant.ADDRESS)
    private String address;

    @OneToMany(mappedBy = "restaurant", fetch = LAZY, cascade = {PERSIST, MERGE, REFRESH})
    @OrderBy("name")
    private List<Menu> menus;

}
