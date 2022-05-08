# MyRestaurant v.2
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/fb0a0e866ff043b2b38e0e9ee3c0db92)](https://www.codacy.com/gh/VaSeBa/MyRestaurant/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=VaSeBa/MyRestaurant&amp;utm_campaign=Badge_Grade)


- Stack: [JDK 17](http://jdk.java.net/17/), Spring Boot 2.5, Lombok, H2, Caffeine Cache, Swagger/OpenAPI 3.0, Mapstruct, Liquibase
- Run: `mvn spring-boot:run` in root directory.
-----------------------------------------------------
[REST API documentation](http://localhost:8080/swagger-ui.html)  
Креденшелы:
```
User:  user@yandex.ru / password
Admin: admin@gmail.com / admin
```

- Протестируйте работу контроллера через Swagger/ браузер/ IDEA Http Client/ Postman

```
[GET] (http://localhost:8080/api/admin/restaurants/1/menu-items)
GET http://localhost:8080/api/admin/restaurants/1/menu-items/1
GET http://localhost:8080/api/admin/restaurants/2/menu-items/4
GET http://localhost:8080/api/admin/restaurants/1/menu-items/by-date?date=2021-06-05
GET http://localhost:8080/api/restaurants
GET http://localhost:8080/api/restaurants/menu_today
GET http://localhost:8080/api/restaurants/1/menu_today


POST http://localhost:8080/api/admin/restaurants/1/menu-items
Authorization: Basic admin@gmail.com admin
Content-Type: application/json

{
  "name": "Хэшбургер с чикенбургером",
  "price": 29500,
  "actualDate": "2021-06-10"
}
###
PUT http://localhost:8080/api/admin/restaurants/1/menu-items/16
Authorization: Basic admin@gmail.com admin
Content-Type: application/json

{
  "name": "Хэшбургер с чикенбургером",
  "price": 19500,
  "actualDate": "2021-06-11"
}
### CONFLICT
PUT http://localhost:8080/api/admin/restaurants/1/menu-items/4
Authorization: Basic admin@gmail.com admin
Content-Type: application/json

{
  "name": "Хэшбургер с чикенбургером",
  "price": 19500,
  "actualDate": "2021-06-11"
}
```


