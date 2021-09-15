package ru.vaseba.myrestaurant.rest;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class RestResources {

    public static class V1 {

        public static class Register {
            public static final String URI = "/api/v1/register/";
        }

        public static class Auth {
            public static final String URI = "/api/v1/login/";
        }

        public static class Restaurant {
            public static final String URI = "/api/v1/restaurants/";
            public static final String ADD_MENU_ACTUAL = "{id}/menus/actual/";
            public static final String MENUS = "menus/";
            public static final String MENUS_ACTUAL = "menus/actual/";
        }

        public static class Menu {
            public static final String URI = "/api/v1/menus/";
        }

        public static class Voice {
            public static final String URI = "/api/v1/voices/";
            public static final String MY = "my";
        }

        public static class Dish {
            public static final String URI = "/api/v1/dishes/";
        }

        public static class User {
            public static final String URI = "/api/v1/users/";
        }

    }

}
