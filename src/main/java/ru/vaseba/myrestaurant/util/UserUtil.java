package ru.vaseba.myrestaurant.util;

import lombok.experimental.UtilityClass;
import ru.vaseba.myrestaurant.model.User;

import static ru.vaseba.myrestaurant.config.SecurityConfiguration.PASSWORD_ENCODER;

@UtilityClass
public class UserUtil {

    public static User prepareToSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}