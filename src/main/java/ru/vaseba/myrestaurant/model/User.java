package ru.vaseba.myrestaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.vaseba.myrestaurant.meta.Meta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = Meta.User.TABLE_NAME, uniqueConstraints = {@UniqueConstraint(columnNames = {Meta.User.EMAIL})})
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"email", "password", "roles", "voices"})
@ToString(callSuper = true, exclude = {"roles", "voices"})
public class User extends BaseNamedEntity implements UserDetails {

    @Email
    @NotBlank
    @Size(min = 3, max = 254)
    // https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address
    @Column(name = Meta.User.EMAIL, nullable = false)
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    @Column(name = Meta.User.PASSWORD)
    private String password;

    @ManyToMany
    @JoinTable(name = Meta.UserRole.TABLE_NAME,
            joinColumns = @JoinColumn(name = Meta.UserRole.USER_COLUMN, updatable = false),
            inverseJoinColumns = @JoinColumn(name = Meta.UserRole.ROLE_COLUMN, updatable = false)
    )
    private Set<Role> roles = Set.of();

    @OneToMany(mappedBy = "user")
    @OrderBy("date DESC")
    private List<Voice> voices;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Status.ACTIVE.equals(getStatus());
    }

}
