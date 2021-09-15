package ru.vaseba.myrestaurant;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties
public class MyRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRestaurantApplication.class, args);
    }

}
