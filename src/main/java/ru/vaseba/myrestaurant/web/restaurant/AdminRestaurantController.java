package ru.vaseba.myrestaurant.web.restaurant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vaseba.myrestaurant.model.Restaurant;
import ru.vaseba.myrestaurant.model.Role;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;
import ru.vaseba.myrestaurant.repository.UserRepository;
import ru.vaseba.myrestaurant.util.validation.AdminRestaurantsUtil;
import ru.vaseba.myrestaurant.web.SecurityUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.vaseba.myrestaurant.util.validation.AdminRestaurantsUtil.REST_URL;
import static ru.vaseba.myrestaurant.util.validation.ValidationUtil.assureIdConsistent;
import static ru.vaseba.myrestaurant.util.validation.ValidationUtil.checkNew;


@RestController
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminRestaurantController {

    private final RestaurantRepository repository;
    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        User user = SecurityUtil.authUser();
        if (user.hasRole(Role.R_ADMIN)) {
            return repository.getIn(user.getAdminRestaurants());
        } else {
            return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Caching(evict = {
            @CacheEvict(value = "restaurants", allEntries = true),
            @CacheEvict(value = "allRestaurantsWithMenu", allEntries = true),
            @CacheEvict(value = "restaurantWithMenu", key = "#id")
    })
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "restaurants", allEntries = true)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        Restaurant created = repository.save(restaurant);
        User user = SecurityUtil.authUser();
        if (user.hasRole(Role.R_ADMIN)) {
            AdminRestaurantsUtil.addRestaurant(user, created.getId());
            userRepository.save(user);
        }
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Caching(evict = {
            @CacheEvict(value = "restaurants", allEntries = true),
            @CacheEvict(value = "allRestaurantsWithMenu", allEntries = true),
            @CacheEvict(value = "restaurantWithMenu", key = "#id")
    })
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        repository.save(restaurant);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "restaurants", allEntries = true),
            @CacheEvict(value = "allRestaurantsWithMenu", allEntries = true),
            @CacheEvict(value = "restaurantWithMenu", key = "#id")
    })
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        Restaurant restaurant = repository.getById(id);
        restaurant.setEnabled(enabled);
    }
}