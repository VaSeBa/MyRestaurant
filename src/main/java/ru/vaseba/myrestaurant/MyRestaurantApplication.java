package ru.vaseba.myrestaurant;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.vaseba.myrestaurant.model.Role;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.model.Voice;
import ru.vaseba.myrestaurant.repository.UserRepository;
import ru.vaseba.myrestaurant.repository.VoiceRepository;

import java.time.LocalDate;
import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class MyRestaurantApplication implements ApplicationRunner {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyRestaurantApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        User testUser1 = new User("user@gmail.com", "password", Set.of(Role.ROLE_USER));
        User testUser2 = new User("admin@javaops.ru", "admin", Set.of(Role.ROLE_USER, Role.ROLE_ADMIN));
        userRepository.save(testUser1);
        userRepository.save(testUser2);
        System.out.println(userRepository.findAll());
    }
}
