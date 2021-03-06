package ru.vaseba.myrestaurant.web;

import lombok.Getter;
import org.springframework.lang.NonNull;
import ru.vaseba.myrestaurant.model.Role;
import ru.vaseba.myrestaurant.model.User;


@Getter
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }

    public boolean hasRole(Role role) {
        return user.hasRole(role);
    }
}