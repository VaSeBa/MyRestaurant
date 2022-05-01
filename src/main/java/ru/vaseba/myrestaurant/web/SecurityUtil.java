package ru.vaseba.myrestaurant.web;

import static java.util.Objects.requireNonNull;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.vaseba.myrestaurant.entity.User;

@UtilityClass
public class SecurityUtil {

    public static AuthUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthUser) ? (AuthUser) principal : null;
    }

    public static AuthUser get() {
        return requireNonNull(safeGet(), "No authorized user found");
    }

    public static User authUser() {
        return get().getUser();
    }

    public static int authId() {
        return get().getUser().id();
    }
}