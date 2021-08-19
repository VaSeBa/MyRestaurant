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
@AllArgsConstructor
public class MyRestaurantApplication implements ApplicationRunner {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    private final VoiceRepository voiceRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyRestaurantApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
//        System.out.println(userRepository.findAll());
        System.out.println(userRepository.findByEmailIgnoreCase("user1@yandex.ru"));
        System.out.println(userRepository.findByNameContainingIgnoreCase("User_Name"));
        System.out.println(restaurantRepository.findAll());
        System.out.println(dishRepository.findAll());
        System.out.println(voiceRepository.findAll());
    }
}
