DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS voice;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurant;

-- region user
CREATE TABLE IF NOT EXISTS user
(
    pk       INT AUTO_INCREMENT PRIMARY KEY,
    uuid     UUID UNIQUE             NOT NULL,
    name     VARCHAR(255)            NOT NULL,
    created  TIMESTAMP DEFAULT now() NOT NULL,
    updated  TIMESTAMP               NULL,
    status   TINYINT   DEFAULT 1     NOT NULL,
    email    VARCHAR(255) UNIQUE     NOT NULL,
    password VARCHAR(255)            NOT NULL
);

COMMENT ON TABLE user
    IS 'Пользователь';

COMMENT ON COLUMN user.pk
    IS 'Первичный ключ';

COMMENT ON COLUMN user.uuid
    IS 'Уникальный идентификатор';

COMMENT ON COLUMN user.created
    IS 'Дата и время создания';

COMMENT ON COLUMN user.updated
    IS 'Дата и время обновления';

COMMENT ON COLUMN user.status
    IS 'Статус:
0 - удалено,
1 - активно,
2 - не активно';

COMMENT ON COLUMN user.name
    IS 'Имя пользователя';

COMMENT ON COLUMN user.email
    IS 'Email пользователя';

COMMENT ON COLUMN user.password
    IS 'Закодированный пароль пользователя';
-- endregion

-- region role
CREATE TABLE IF NOT EXISTS role
(
    pk      SMALLINT AUTO_INCREMENT PRIMARY KEY,
    uuid    UUID UNIQUE             NOT NULL,
    name    VARCHAR(255) UNIQUE     NOT NULL,
    created TIMESTAMP DEFAULT now() NOT NULL,
    updated TIMESTAMP               NULL,
    status  TINYINT   DEFAULT 1     NOT NULL
);

COMMENT ON TABLE role
    IS 'Роль';

COMMENT ON COLUMN role.pk
    IS 'Первичный ключ';

COMMENT ON COLUMN role.uuid
    IS 'Уникальный идентификатор';

COMMENT ON COLUMN role.name
    IS 'Название роли';

COMMENT ON COLUMN role.created
    IS 'Дата и время создания';

COMMENT ON COLUMN role.updated
    IS 'Дата и время обновления';

COMMENT ON COLUMN role.status
    IS 'Статус:
0 - удалено,
1 - активно,
2 - не активно';
-- endregion

-- region user_role
CREATE TABLE IF NOT EXISTS user_role
(
    user_uuid UUID NOT NULL,
    role_uuid UUID NOT NULL,
    FOREIGN KEY (user_uuid) REFERENCES user (uuid),
    FOREIGN KEY (role_uuid) REFERENCES role (uuid),
    CONSTRAINT uniq_user_role UNIQUE (user_uuid, role_uuid)
);

COMMENT ON TABLE user_role
    IS 'Роль пользователя';

COMMENT ON COLUMN user_role.user_uuid
    IS 'Ссылка на пользователя';

COMMENT ON COLUMN user_role.role_uuid
    IS 'Ссылка на роль';
-- endregion

-- region restaurant
CREATE TABLE IF NOT EXISTS restaurant
(
    pk      INT AUTO_INCREMENT PRIMARY KEY,
    uuid    UUID UNIQUE             NOT NULL,
    name    VARCHAR(255)            NOT NULL,
    created TIMESTAMP DEFAULT now() NOT NULL,
    updated TIMESTAMP               NULL,
    status  TINYINT   DEFAULT 1     NOT NULL,
    address VARCHAR(255)            NOT NULL,
    email   VARCHAR(255)            NOT NULL,
    CONSTRAINT uniq_restaurant UNIQUE (name, address)
);

COMMENT ON TABLE restaurant
    IS 'Ресторан';

COMMENT ON COLUMN restaurant.pk
    IS 'Первичный ключ';

COMMENT ON COLUMN restaurant.uuid
    IS 'Уникальный идентификатор';

COMMENT ON COLUMN restaurant.name
    IS 'Название ресторана';

COMMENT ON COLUMN restaurant.created
    IS 'Дата и время создания';

COMMENT ON COLUMN restaurant.updated
    IS 'Дата и время обновления';

COMMENT ON COLUMN restaurant.status
    IS 'Статус:
0 - удалено,
1 - активно,
2 - не активно';

COMMENT ON COLUMN restaurant.address
    IS 'Адрес ресторана';

COMMENT ON COLUMN restaurant.email
    IS 'Email ресторана';
-- endregion

-- region menu
CREATE TABLE IF NOT EXISTS menu
(
    pk              INT AUTO_INCREMENT PRIMARY KEY,
    uuid            UUID UNIQUE             NOT NULL,
    name            VARCHAR(255)            NOT NULL,
    created         TIMESTAMP DEFAULT now() NOT NULL,
    updated         TIMESTAMP               NULL,
    status          TINYINT   DEFAULT 1     NOT NULL,
    restaurant_uuid UUID                    NOT NULL,
    actual          BOOLEAN                 NOT NULL,
    FOREIGN KEY (restaurant_uuid) REFERENCES restaurant (uuid) ON DELETE CASCADE
);

COMMENT ON TABLE menu
    IS 'Меню ресторана';

COMMENT ON COLUMN menu.pk
    IS 'Первичный ключ';

COMMENT ON COLUMN menu.uuid
    IS 'Уникальный идентификатор';

COMMENT ON COLUMN menu.name
    IS 'Название меню';

COMMENT ON COLUMN menu.created
    IS 'Дата и время создания';

COMMENT ON COLUMN menu.updated
    IS 'Дата и время обновления';

COMMENT ON COLUMN menu.status
    IS 'Статус:
0 - удалено,
1 - активно,
2 - не активно';

COMMENT ON COLUMN menu.restaurant_uuid
    IS 'Ссылка на ресторан';

COMMENT ON COLUMN menu.actual
    IS 'Флаг - является ли меню актуальным сегодня';
-- endregion

-- region dish
CREATE TABLE IF NOT EXISTS dish
(
    pk        INT AUTO_INCREMENT PRIMARY KEY,
    uuid      UUID UNIQUE             NOT NULL,
    name      VARCHAR(255)            NOT NULL,
    created   TIMESTAMP DEFAULT now() NOT NULL,
    updated   TIMESTAMP               NULL,
    status    TINYINT   DEFAULT 1     NOT NULL,
    menu_uuid UUID                    NOT NULL,
    price     BIGINT                  NOT NULL,
    FOREIGN KEY (menu_uuid) REFERENCES menu (uuid) ON DELETE CASCADE
);

COMMENT ON TABLE dish
    IS 'Блюдо';

COMMENT ON COLUMN dish.pk
    IS 'Первичный ключ';

COMMENT ON COLUMN dish.uuid
    IS 'Уникальный идентификатор';

COMMENT ON COLUMN dish.name
    IS 'Название блюда';

COMMENT ON COLUMN dish.created
    IS 'Дата и время создания';

COMMENT ON COLUMN dish.updated
    IS 'Дата и время обновления';

COMMENT ON COLUMN dish.status
    IS 'Статус:
0 - удалено,
1 - активно,
2 - не активно';

COMMENT ON COLUMN dish.menu_uuid
    IS 'Ссылка на меню ресторана';

COMMENT ON COLUMN dish.price
    IS 'Стоимость блюда в копейках';
-- endregion

-- region voice
CREATE TABLE IF NOT EXISTS voice
(
    pk        INT AUTO_INCREMENT PRIMARY KEY,
    uuid      UUID UNIQUE             NOT NULL,
    created   TIMESTAMP DEFAULT now() NOT NULL,
    updated   TIMESTAMP               NULL,
    status    TINYINT   DEFAULT 1     NOT NULL,
    user_uuid UUID                    NOT NULL,
    date      DATE      DEFAULT now() NOT NULL,
    menu_uuid UUID                    NOT NULL,
    FOREIGN KEY (user_uuid) REFERENCES user (uuid),
    FOREIGN KEY (menu_uuid) REFERENCES menu (uuid),
    CONSTRAINT uniq_voice UNIQUE (user_uuid, date)
);

COMMENT ON TABLE voice
    IS 'Голос пользователя за меню';

COMMENT ON COLUMN voice.pk
    IS 'Первичный ключ';

COMMENT ON COLUMN voice.uuid
    IS 'Уникальный идентификатор';

COMMENT ON COLUMN voice.created
    IS 'Дата и время создания';

COMMENT ON COLUMN voice.updated
    IS 'Дата и время обновления';

COMMENT ON COLUMN voice.status
    IS 'Статус:
0 - удалено,
1 - активно,
2 - не активно';

COMMENT ON COLUMN voice.user_uuid
    IS 'Ссылка на пользователя';

COMMENT ON COLUMN voice.date
    IS 'Дата голосования';

COMMENT ON COLUMN voice.menu_uuid
    IS 'Ссылка на меню ресторана';
-- endregion