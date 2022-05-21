INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (NAME, ADDRESS, ENABLED)
VALUES ('Макдоналдс', 'ул. Зеленая, 31', true),
       ('Шаляпин', 'ул. Мира, 67', false),
       ('Васаби', 'ул. Бумажная, д.20', true);

INSERT INTO MENU_ITEM (NAME, PRICE, ACTUAL_DATE, RESTAURANT_ID)
VALUES
--- Актуальная дата
('Филе-о-Фиш', 12700, CURRENT_DATE, 1),
('Чикенбургер', 5000, CURRENT_DATE, 1),
('Чикен Макнаггетс (20шт)', 27200, CURRENT_DATE, 1),

('Борщ с фасолью и чесночной пампушкой', 49000, CURRENT_DATE, 2),
('Рассольник по-шаляпински', 55000, CURRENT_DATE, 2),
('Шницель из телятины с квашеной капустой и печеным яблоком', 95000, CURRENT_DATE, 2),

('Ролл Сочная креветка', 25700, CURRENT_DATE, 3),
('Ролл Огонь', 31700, CURRENT_DATE, 3),
('Ролл Калифорния с цыпленком', 12900, CURRENT_DATE, 3),

--- не Актуальная дата
('Филе-о-Фиш', 12700, '2022-05-05', 1),
('Борщ с фасолью и чесночной пампушкой', 49000, '2022-05-05', 2),
('Ролл Сочная креветка', 25700, '2022-05-05', 3),

--- не Актуальная дата - 1 день
('Чикенбургер', 5000, '2022-05-04', 1),
('Рассольник по-шаляпински', 55000, '2022-05-04', 2),
('Ролл Огонь', 31700, '2022-05-04', 3);

INSERT INTO VOTE (USER_ID, ACTUAL_DATE, ACTUAL_TIME, RESTAURANT_ID)
VALUES (1, CURRENT_DATE, '12:30:00', 1),
       (1, '2022-05-21', '09:15:00', 1),
       (1, '2022-05-20', '15:55:00', 3),
       (2, '2022-05-21', '08:15:00', 2),
       (2, '2022-05-20', '12:55:00', 3);