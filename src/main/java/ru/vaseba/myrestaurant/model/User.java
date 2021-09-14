package ru.vaseba.myrestaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"email", "password", "roles", "voices"})
public class User extends BaseNamedEntity {

    @Email
    @NotBlank
    @Size(min = 3, max = 254)
    // https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_uuid", updatable = false),
            inverseJoinColumns = @JoinColumn(name = "role_uuid", updatable = false)
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    @OrderBy("date DESC")
    private List<Voice> voices;

}
