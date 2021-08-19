INSERT INTO users (name, email, password)
VALUES ('User_Name', 'user1@yandex.ru', 'user1Password'),
       ('User2', 'user2@yandex.ru', 'user2Password'),
       ('Admin_Name', 'admin@yandex.ru', 'adminPassword');

INSERT INTO restaurant (name) VALUES
('Москва'),
('Кухни мира');

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
(1, 1, '2021-08-19'),
(2, 1, '2021-08-19'),
(1, 1, '2021-08-19'),
(2, 1, '2021-08-19'),
(1, 2, '2021-08-19'),
(2, 2, '2021-08-19');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 1),
       ('ROLE_ADMIN', 2),
       ('ROLE_USER', 2);

