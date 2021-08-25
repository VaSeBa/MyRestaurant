package ru.vaseba.myrestaurant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.repository.UserRepository;
import ru.vaseba.myrestaurant.util.ValidationUtil;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@Tag(name = "User Controller")
@RequestMapping(value = UserController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    static final String URL = "api/admin/users";

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> createWithLocation(@RequestBody User user) {
        User created = userRepository.save(user);
        log.info("create user: {}", user);
        ValidationUtil.checkNew(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/by")
    public Optional<User> getByEmail(@RequestParam String email) {
        Optional<User> user = userRepository.findByEmailIgnoreCase(email);
        log.info("get by email: {}, user: {}", email, user);
        return user;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user, @PathVariable int id) {
        log.info("update user: {} id: {}, ", user, id);
        ValidationUtil.assureIdConsistent(user, id);
        if (user.getPassword() == null) {
            User u = userRepository.getById(id);
            u.setPassword(u.getPassword());
        }
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete user id: {}", id);
        userRepository.deleteById(id);
    }

}
