# MyRestaurant REST API using Hibernate/Spring/Spring-Boot without frontend
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/fb0a0e866ff043b2b38e0e9ee3c0db92)](https://www.codacy.com/gh/VaSeBa/MyRestaurant/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=VaSeBa/MyRestaurant&amp;utm_campaign=Badge_Grade)

# Voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we assume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides a new menu each day.


# Stack 

- Java 14
- Spring Boot 2.5
- Lombok 
- H2 Database
- Swagger/OpenAPI 3.0 
- Caffeine Cache
- Maven
- Spring Data JPA
- Spring Security

# You can download this application

git clone https://github.com/VaSeBa/MyRestaurant.git
then start
mvn spring-boot:run (in bash)


# Swagger (REST API documentation)

http://localhost:8080/swagger-ui.html
http://localhost:8080/v3/api-docs

# Curl 

### get all user (for admin)
`curl -s http://localhost:8080/api/admin/users --user admin@javaops.ru:admin`
### get user for id (for admin)
`curl -s http://localhost:8080/api/admin/users/(select id of user) --user admin@javaops.ru:admin`
### get all restaurant 
`curl -s http://localhost:8080/api/restaurants --user admin@javaops.ru:admin`
### get restaurant for id 
`curl -s http://localhost:8080/api/restaurants/(select id of user) --user admin@javaops.ru:admin`
### get all dishes 
`curl -s http://localhost:8080/api/dishes --user admin@javaops.ru:admin`
### get dish for id 
`curl -s http://localhost:8080/api/dishes/1 (select id of user) --user admin@javaops.ru:admin`
### get dish for id and restaurant
`curl -s http://localhost:8080/api/admin/restaurants/{restaurantsId}/dishes --user admin@javaops.ru:admin`
### get all voice (for admin)
`curl -s http://localhost:8080/api/voices --user admin@javaops.ru:admin`

### get user account 
`curl -s http://localhost:8080/api/account --user first_user@gmail.com:password1`

# HTTP requests

### add new User

`POST http://localhost:8080/api/account/register
Content-Type: application/json

{
      "email": "test@test.com",
      "name": "Test",
      "password": "test"
}`

### add Dish in Restaurant

`POST http://localhost:8080/api/admin/restaurants/1/dishes
Authorization: Basic admin@javaops.ru admin
Content-Type: application/json

{
  "name": "new",
  "price": 100
}`

### add Restaurent

`POST http://localhost:8080/api/admin/restaurants
Authorization: Basic admin@javaops.ru admin
Content-Type: application/json

{
  "name": "New"
}`






