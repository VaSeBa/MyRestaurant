package ru.vaseba.myrestaurant.meta;

import lombok.NoArgsConstructor;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class Meta {

    @NoArgsConstructor(access = PRIVATE)
    public static class BaseEntity {
        public static final String ID_COLUMN = "uuid";
        public static final String ID_FIELD = "id";
        public static final String CREATED = "created";
        public static final String UPDATED = "updated";
        public static final String STATUS = "status";
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class BaseNamedEntity extends BaseEntity {
        public static final String NAME = "name";
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Dish extends BaseNamedEntity {
        public static final String TABLE_NAME = "dish";
        public static final String MENU_COLUMN = "menu_uuid";
        public static final String MENU_FIELD = "menu";
        public static final String PRICE = "price";
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Menu extends BaseNamedEntity {
        public static final String TABLE_NAME = "menu";
        public static final String RESTAURANT_COLUMN = "restaurant_uuid";
        public static final String RESTAURANT_FIELD = "restaurant";
        public static final String ACTUAL = "actual";
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Restaurant extends BaseNamedEntity {
        public static final String TABLE_NAME = "restaurant";
        public static final String EMAIL = "email";
        public static final String ADDRESS = "address";
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Role extends BaseNamedEntity {
        public static final String TABLE_NAME = "role";
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class User extends BaseNamedEntity {
        public static final String TABLE_NAME = "user";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class UserRole {
        public static final String TABLE_NAME = "user_role";
        public static final String USER_COLUMN = "user_uuid";
        public static final String USER_FIELD = "user";
        public static final String ROLE_COLUMN = "role_uuid";
        public static final String ROLE_FIELD = "role";
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Voice extends BaseEntity {
        public static final String TABLE_NAME = "voice";
        public static final String USER_COLUMN = "user_uuid";
        public static final String USER_FIELD = "user";
        public static final String DATE = "date";
        public static final String MENU_COLUMN = "menu_uuid";
        public static final String MENU_FIELD = "menu";
    }
}
