package ru.vaseba.myrestaurant.service;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class Cache {

    public static class Restaurant {
        public static final String ALL = "restaurants";
        public static final String ALL_WITH_ACTUAL_MENUS = "restaurantsWithActualMenus";
    }

    public static class User {
        public static final String BY_EMAIL = "userByEmail";
    }

}
