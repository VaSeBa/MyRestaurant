INSERT INTO users (name, email, password)
VALUES ('User_Name', 'first_user@gmail.com', '{noop}password1'),
       ('User_Name', 'second_user@gmail.com', '{noop}password2'),
       ('Admin_Name', 'admin@javaops.ru', '{noop}admin');

INSERT INTO restaurant (id, name) VALUES
(1, 'Москва'),
(2, 'Кухни мира');

INSERT INTO dish (name, price, restaurants_id) VALUES
('Щи', 100, 1),
('Блины', 150, 1),
('Щука запеченая с картофелем', 350, 1),
('Пюре с котлетами', 200, 1),
('Хачапури', 150, 1),
('Хинкали', 250, 2),
('Ачма', 150, 2),
('Унаги', 150, 2),
('Рамен', 100, 2),
('Суши', 150, 2);

INSERT INTO voice (user_id, restaurants_id, time_of_voicing) VALUES
(1, 1, today),
(2, 2, '2021-08-19');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('USER', 2),
       ('ADMIN', 3),
       ('USER', 3);

