# MyRestaurant <br> REST API using Hibernate/Spring/Spring-Boot without frontend
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/fb0a0e866ff043b2b38e0e9ee3c0db92)](https://www.codacy.com/gh/VaSeBa/MyRestaurant/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=VaSeBa/MyRestaurant&amp;utm_campaign=Badge_Grade)

# MyRestaurant 

MyRestaurant - это реализация [выпускного проекта](graduation.md "Полное описание проекта") по итогам курса "TopJava".

# Stack 

* Java 16 
* Maven
* Spring Boot
* Spring Security
* Spring Web
* Spring Data JPA
* Lombok
* H2 Database
* Flyway Migration
* Swagger 2


# Как пользоваться

Копируем проект к себе на ПК
<br>
git clone `https://github.com/VaSeBa/MyRestaurant.git`
<br>
затем в корне проекта запускаем через Bash
<br>
mvn spring-boot:run


# Swagger (REST API documentation) - документация

После запуска приложения, его документация будет доступна по ссылке: http://localhost:8080/swagger-ui.html.
Чтобы выполнить запрос, нужно:
1. Выбрать ресурс, например, `auth-rest-controller-v-1` -> `POST /api/v1/login/`, который авторизует пользователя
2. Раскрыть его, нажать на кнопку `Try it out`
3. После чего нажать на появившуюся кнопку `Execute`


JWT-токен из ответа выше потребуется для выполнения тех запросов, где требуется авторизация (подробнее в таблицах ниже).

Для авторизации запросов в Swagger необходимо:
1. Скопировать токен
2. Справа вверху страницы нажать на кнопку `Authorize`
3. В появившемся окне ввести в поле "Value:" значение `Bearer_` и вставить скопированный токен
4. Нажать на кнопку `Authorize`

После этих действий все запросы будут отправляться с правами пользователя-владельца токена.

Для изменения авторизационных данных необходимо сбросить текущий токен - нажать на кнопку `Authorize` (справа вверху
страницы) и в появившемся окне нажать `Logout`, либо обновить страницу по "F5".

После чего нужно будет получить новый токен и авторизоваться с ним.

# Curl - запросы

Для выполнения запросов,
где требуется авторизация необходимо заменять значение "[token]" на валидный токен пользователя с нужной ролью.
Токен получается запросом ["Авторизация пользователя"](#Авторизация-пользователя).

## Запросы не авторизованных пользователей

* Просмотр списка ресторанов с их меню дня
```bash
curl -X GET "http://localhost:8080/api/v1/restaurants/menus/actual/" -H "accept: application/json"
```

* Регистрация нового пользователя
```bash
curl -X POST "http://localhost:8080/api/v1/register/" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"name\": \"New User\", \"email\": \"new_user@foodvoice.ru\", \"password\": \"password\"}"
```

* Авторизация пользователя
```bash
curl -X POST "http://localhost:8080/api/v1/login/" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"email\": \"admin@foodvoice.ru\", \"password\": \"password\"}"
```

## Запросы авторизованные пользователей

* Выбор ресторан (его меню) для обеда
```bash
curl -X POST "http://localhost:8080/api/v1/voices/" -H "accept: application/json" -H "Authorization: Bearer_[token]" -H "Content-Type: application/json" -d "{ \"menuId\": \"dd0e6ec6-1532-4219-bab4-2e04a31b6a01\"}"
```

* Изменение выбранного ресторана
```bash
curl -X PUT "http://localhost:8080/api/v1/voices/f16eb5ea-14bc-42b0-809a-333639464730" -H "accept: application/json" -H "Authorization: Bearer_[token]" -H "Content-Type: application/json" -d "{ \"menuId\": \"dd0e6ec6-1532-4219-bab4-2e04a31b6a01\"}"
```

* Просмотр история своего голосования
```bash
curl -X GET "http://localhost:8080/api/v1/voices/my" -H "accept: application/json" -H "Authorization: Bearer_[token]"
```

## Запросы авторизованных администраторов

* Просмотр списка ресторанов с их меню
```bash
curl -X GET "http://localhost:8080/api/v1/restaurants/menus/" -H "accept: application/json" -H "Authorization: Bearer_[curl -X PUT "http://localhost:8080/api/v1/voices/f16eb5ea-14bc-42b0-809a-333639464730" -H "accept: application/json" -H "Authorization: Bearer_[token]" -H "Content-Type: application/json" -d "{ \"menuId\": \"dd0e6ec6-1532-4219-bab4-2e04a31b6a01\"}"]"
```

* Просмотр списка ресторанов с их меню
```bash
curl -X GET "http://localhost:8080/api/v1/restaurants/menus/" -H "accept: application/json" -H "Authorization: Bearer_[token]"
```

* Просмотр ресторана с его меню
```bash
curl -X GET "http://localhost:8080/api/v1/restaurants/58b3d8b0-7d08-4274-8aa8-68976d0582ee" -H "accept: application/json" -H "Authorization: Bearer_[token]"
```

* Добавление актуального меню ресторана
```bash
curl -X POST "http://localhost:8080/api/v1/restaurants/58b3d8b0-7d08-4274-8aa8-68976d0582ee/menus/actual/" -H "accept: application/json" -H "Authorization: Bearer_[token]" -H "Content-Type: application/json" -d "{ \"name\": \"name\", \"status\": 1, \"actual\": true, \"dishes\": [ { \"name\": \"name\", \"status\": 1, \"price\": 100000 } ]}"
```

* Просмотр меню ресторана
```bash
curl -X GET "http://localhost:8080/api/v1/menus/fbb9b1a5-a599-4574-954c-27b52a95be2a" -H "accept: application/json" -H "Authorization: Bearer_[token]"
```

* Изменение/установка меню дня ресторана
```bash
curl -X PUT "http://localhost:8080/api/v1/menus/fbb9b1a5-a599-4574-954c-27b52a95be2a?actual=true" -H "accept: application/json" -H "Authorization: Bearer_[token]"
```

* Изменение пользователя
```bash
curl -X PUT "http://localhost:8080/api/v1/users/f16eb5ea-14bc-42b0-809a-333639464730" -H "accept: application/json" -H "Authorization: Bearer_[token]" -H "Content-Type: application/json" -d "{ \"name\": \"name\", \"status\": 1, \"email\": \"new@foodvoice.ru\", \"password\": \"password\", \"roles\": [ { \"name\": \"USER\" }, { \"name\": \"ADMIN\" } ]}"
```

