DELETE
FROM user_role
WHERE user_uuid IS NOT NULL;
DELETE
FROM voice
WHERE user_uuid IS NOT NULL;
DELETE
FROM user
WHERE uuid IS NOT NULL;
DELETE
FROM role
WHERE uuid IS NOT NULL;
DELETE
FROM dish
WHERE uuid IS NOT NULL;
DELETE
FROM menu
WHERE uuid IS NOT NULL;
DELETE
FROM restaurant
WHERE uuid IS NOT NULL;

-- region user
INSERT INTO user(uuid, name, email, password)
-- password
VALUES ('0151e05d-3185-4304-b9dd-9fc8f97db290', 'Администратор', 'admin@foodvoice.ru',
        '$2a$10$hyWbYTkvbBw7EvI0q9VMyuwvzMfaYtYO1SR01z4fLcPlfLpGpfuzS'),
       ('f16eb5ea-14bc-42b0-809a-333639464730', 'Пользователь', 'user@foodvoice.ru',
        '$2a$10$hyWbYTkvbBw7EvI0q9VMyuwvzMfaYtYO1SR01z4fLcPlfLpGpfuzS');

INSERT INTO user(uuid, name, status, email, password)
VALUES ('dfa59084-ff1d-4386-a801-fa6f75ccc2c7', 'DELETED', 0, 'DELETED@foodvoice.ru',
        '$2a$10$hyWbYTkvbBw7EvI0q9VMyuwvzMfaYtYO1SR01z4fLcPlfLpGpfuzS'),
       ('acd6aa59-c7f8-4c0f-9b94-18ecc1381cc5', 'NOT_ACTIVE', 2, 'NOT_ACTIVE@foodvoice.ru',
        '$2a$10$hyWbYTkvbBw7EvI0q9VMyuwvzMfaYtYO1SR01z4fLcPlfLpGpfuzS');
-- endregion

-- region role
INSERT INTO role(uuid, name)
VALUES ('2a9cf406-7f34-4cc7-a4d0-d4f12056a9d9', 'ADMIN'),
       ('1c99e5d3-0bc3-4607-b867-1cde3ce160ce', 'USER');

INSERT INTO role(uuid, name, status)
VALUES ('9876f5b5-f342-4045-8092-6896fbbf0a09', 'DELETED', 0),
       ('6eb53eb7-df90-4c3e-8b99-7019174cc06b', 'NOT_ACTIVE', 2);
-- endregion

-- region user_role
INSERT INTO user_role(user_uuid, role_uuid)
VALUES ('0151e05d-3185-4304-b9dd-9fc8f97db290', '2a9cf406-7f34-4cc7-a4d0-d4f12056a9d9'),
       ('0151e05d-3185-4304-b9dd-9fc8f97db290', '1c99e5d3-0bc3-4607-b867-1cde3ce160ce'),
       ('f16eb5ea-14bc-42b0-809a-333639464730', '1c99e5d3-0bc3-4607-b867-1cde3ce160ce'),
       ('dfa59084-ff1d-4386-a801-fa6f75ccc2c7', '9876f5b5-f342-4045-8092-6896fbbf0a09'),
       ('acd6aa59-c7f8-4c0f-9b94-18ecc1381cc5', '6eb53eb7-df90-4c3e-8b99-7019174cc06b');
-- endregion

-- region restaurant
INSERT INTO restaurant(uuid, name, address, email)
VALUES ('58b3d8b0-7d08-4274-8aa8-68976d0582ee', 'Месье Колбасье', 'Город, Улица, дом', 'monsieur_sausage@foodvoice.ru'),
       ('01f8e5df-ac2e-4898-b72c-ac62123c21ed', 'Звери как звери', 'Город, Улица, дом',
        'animals_like_animals@foodvoice.ru'),
       ('7c34d5b3-634f-4b75-abf3-49a40fcc18ec', 'Звери как звери', 'Адрес №2', 'animals_like_animals@foodvoice.ru');

INSERT INTO restaurant(uuid, name, status, address, email)
VALUES ('8bad8470-06bb-4b15-b98b-95745152875b', 'DELETED', 0, 'DELETED', 'DELETED@foodvoice.ru'),
       ('f18d3bcc-492d-4e14-8442-dcf5481d5fae', 'NOT_ACTIVE', 2, 'NOT_ACTIVE', 'NOT_ACTIVE@foodvoice.ru');
-- endregion

-- region menu
INSERT INTO menu(uuid, name, restaurant_uuid, actual)
VALUES ('dd0e6ec6-1532-4219-bab4-2e04a31b6a01', 'Русская кухня', '58b3d8b0-7d08-4274-8aa8-68976d0582ee', 'true'),
       ('fbb9b1a5-a599-4574-954c-27b52a95be2a', 'Русское меню', '58b3d8b0-7d08-4274-8aa8-68976d0582ee', 'false'),
       ('62e1e74e-ecd7-4d82-a41a-325f91aeddf1', 'Европейская кухня', '01f8e5df-ac2e-4898-b72c-ac62123c21ed', 'true'),
       ('d66a3d54-85b0-4e51-b2f8-372963bbacf7', 'Европейское меню', '01f8e5df-ac2e-4898-b72c-ac62123c21ed', 'false');

INSERT INTO menu(uuid, name, status, restaurant_uuid, actual)
VALUES ('7df4e03b-3058-4470-b5df-6ccff74effb6', 'DELETED', 0, '8bad8470-06bb-4b15-b98b-95745152875b', 'true'),
       ('5d50156e-aec3-4c78-8d34-359a4c909614', 'NOT_ACTIVE', 2, 'f18d3bcc-492d-4e14-8442-dcf5481d5fae', 'true');
-- endregion

-- region dish
INSERT INTO dish(uuid, name, menu_uuid, price)
VALUES ('a29de34f-82cb-4fc3-a3f4-7fc37398b987', 'Блины', 'dd0e6ec6-1532-4219-bab4-2e04a31b6a01', 10000),
       ('e43f72a2-0ee4-4f1f-9f61-ff3d185438ce', 'Селёдка под шубой', 'dd0e6ec6-1532-4219-bab4-2e04a31b6a01', 10000),
       ('3007713f-223d-4d66-8d77-8ce0b9d97147', 'ЩИ', 'dd0e6ec6-1532-4219-bab4-2e04a31b6a01', 10000),
       ('e8cf21e1-4de1-46a3-8f2c-bae6fc87c579', 'Квас', 'dd0e6ec6-1532-4219-bab4-2e04a31b6a01', 10000),
       ('61c07aa6-3099-4e33-a29c-493e04f969f3', 'Цезарь', '62e1e74e-ecd7-4d82-a41a-325f91aeddf1', 10000),
       ('d15c5f85-cfdf-43d1-a648-d9560031e452', 'Грильята', '62e1e74e-ecd7-4d82-a41a-325f91aeddf1', 10000),
       ('1e471d70-e992-41d2-a98f-490a9dc916b0', 'Пицца', '62e1e74e-ecd7-4d82-a41a-325f91aeddf1', 10000),
       ('46a22c10-d830-456c-b62c-7f8395cfaf3d', 'Вино', '62e1e74e-ecd7-4d82-a41a-325f91aeddf1', 10000);

INSERT INTO dish(uuid, name, status, menu_uuid, price)
VALUES ('07a09099-de81-4eb6-886e-18332dbdafa9', 'DELETED', 0, '7df4e03b-3058-4470-b5df-6ccff74effb6', 10000),
       ('9d75bedd-3f32-43b2-b3aa-62a8b66f2556', 'NOT_ACTIVE', 2, '5d50156e-aec3-4c78-8d34-359a4c909614', 10000);
-- endregion

-- region voice
INSERT INTO voice(uuid, user_uuid, date, menu_uuid)
VALUES ('46acb07f-a6ca-42c0-9c7c-44a3e3aad71b', '0151e05d-3185-4304-b9dd-9fc8f97db290', '2020-05-08',
        'dd0e6ec6-1532-4219-bab4-2e04a31b6a01'),
       ('00813cff-8e50-4a74-9c85-bbbf90d026ae', 'f16eb5ea-14bc-42b0-809a-333639464730', '2020-05-09',
        '62e1e74e-ecd7-4d82-a41a-325f91aeddf1');

INSERT INTO voice(uuid, status, user_uuid, date, menu_uuid)
VALUES ('db248cbb-7c6a-4fd9-9424-2678d6db4a1c', 0, 'dfa59084-ff1d-4386-a801-fa6f75ccc2c7', '2020-05-05',
        '7df4e03b-3058-4470-b5df-6ccff74effb6'),
       ('a723c2c0-38a2-46fd-b283-0a2be67a7417', 2, 'acd6aa59-c7f8-4c0f-9b94-18ecc1381cc5', '2020-05-05',
        '5d50156e-aec3-4c78-8d34-359a4c909614');
-- endregion