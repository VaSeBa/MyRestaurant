DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS restaurants CASCADE;
DROP TABLE IF EXISTS meals CASCADE;
DROP TABLE IF EXISTS votes CASCADE;

-- DROP SEQUENCE IF EXISTS global_seq;
--
-- CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name             VARCHAR                 NOT NULL,
    email            VARCHAR                 NOT NULL,
    password         VARCHAR                 NOT NULL,
    registered       TIMESTAMP DEFAULT now() NOT NULL,
    enabled          BOOL      DEFAULT TRUE  NOT NULL,
    calories_per_day INTEGER   DEFAULT 2000  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id      INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    enabled BOOL DEFAULT TRUE NOT NULL,
    name    VARCHAR           NOT NULL,
    address VARCHAR           NOT NULL,
    CONSTRAINT restaurant_name UNIQUE (name)
);


CREATE TABLE menu_item
(
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          VARCHAR            NOT NULL,
    price         NUMERIC(7, 2)      NOT NULL,
    actual_date   DATE DEFAULT now() NOT NULL,
    restaurant_id INTEGER            NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    date          DATE    NOT NULL,
    time          TIME    NOT NULL,
    user_id       INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);