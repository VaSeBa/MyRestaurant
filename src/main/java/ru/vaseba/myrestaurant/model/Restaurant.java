package ru.vaseba.myrestaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"})})
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"email", "address"})
public class Restaurant extends BaseNamedEntity {

    @Email
    @NotBlank
    @Size(min = 3, max = 254)
    // https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;

}
