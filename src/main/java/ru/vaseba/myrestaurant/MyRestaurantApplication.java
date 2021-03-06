package ru.vaseba.myrestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class MyRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRestaurantApplication.class, args);
    }
}
