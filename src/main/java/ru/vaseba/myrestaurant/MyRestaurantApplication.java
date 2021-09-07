package ru.vaseba.myrestaurant;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class MyRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRestaurantApplication.class, args);
    }

}
