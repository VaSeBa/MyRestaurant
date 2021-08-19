INSERT INTO users (name, email, password)
VALUES ('User_Name', 'user1@yandex.ru', 'user1Password'),
       ('User2', 'user2@yandex.ru', 'user2Password'),
       ('Admin_Name', 'admin@yandex.ru', 'adminPassword');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 1),
       ('ROLE_ADMIN', 2),
       ('ROLE_USER', 2);

