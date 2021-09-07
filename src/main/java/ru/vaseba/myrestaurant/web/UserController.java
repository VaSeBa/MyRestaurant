package ru.vaseba.myrestaurant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@Tag(name = "User Controller")
@RequestMapping(value = UserController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    static final String URL = "/api/admin/users";

    public final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("get all users: {}", users);
        return users;
    }

    @GetMapping(value = "/{id}")
    public Optional<User> getById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        log.info("get user by id: {}, user: {}", id, user);
        return user;
    }

    @GetMapping("/by")
    public Optional<User> getByEmail(@RequestParam String email) {
        Optional<User> user = userRepository.findByEmailIgnoreCase(email);
        log.info("get by email: {}, user: {}", email, user);
        return user;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete user id: {}", id);
        userRepository.deleteById(id);
    }

}
