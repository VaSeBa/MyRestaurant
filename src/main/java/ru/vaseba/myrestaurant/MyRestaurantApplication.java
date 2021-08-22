package ru.vaseba.myrestaurant;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.vaseba.myrestaurant.model.Role;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.model.Voice;
import ru.vaseba.myrestaurant.repository.DishRepository;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;
import ru.vaseba.myrestaurant.repository.UserRepository;
import ru.vaseba.myrestaurant.repository.VoiceRepository;

import java.time.LocalDate;
import java.util.Set;

@SpringBootApplication
public class MyRestaurantApplication {


    public static void main(String[] args) {
        SpringApplication.run(MyRestaurantApplication.class, args);
    }

}
