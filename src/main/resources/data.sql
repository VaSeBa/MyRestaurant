INSERT INTO users (name, email, password)
VALUES ('User_Name', 'first_user@gmail.com', '{noop}password1'),
       ('Second_User_Name', 'second_user@gmail.com', '{noop}password2'),
       ('Admin_Name', 'admin@javaops.ru', '{noop}admin');

INSERT INTO restaurant (id, name) VALUES
(1, 'Москва'),
(2, 'Кухни мира');

INSERT INTO dish (name, price, restaurants_id, date) VALUES
('Щи', 100, 1, today),
('Блины', 150, 1, today),
('Щука запеченая с картофелем', 350, 1, today),
('Пюре с котлетами', 200, 1, today),
('Хачапури', 150, 1, today),
('Хинкали', 250, 2, today),
('Ачма', 150, 2, today),
('Унаги', 150, 2, today),
('Рамен', 100, 2, today),
('Суши', 150, 2, today);

INSERT INTO voice (id, user_id, restaurants_id, date) VALUES
(1, 1, 1, today),
(2, 2, 2, '2021-08-19');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 1),
       ('ROLE_USER', 2),
       ('ROLE_ADMIN', 3),
       ('ROLE_USER', 3);

