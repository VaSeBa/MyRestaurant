package ru.vaseba.myrestaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import ru.vaseba.myrestaurant.meta.Meta;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = Meta.Role.TABLE_NAME, uniqueConstraints = {@UniqueConstraint(columnNames = {Meta.Role.NAME})})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Role extends BaseNamedEntity implements GrantedAuthority {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    @Override
    public String getAuthority() {
        return "ROLE_" + getName();
    }

}
