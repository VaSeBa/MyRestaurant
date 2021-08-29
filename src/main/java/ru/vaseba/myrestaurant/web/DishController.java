package ru.vaseba.myrestaurant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vaseba.myrestaurant.model.Dish;
import ru.vaseba.myrestaurant.repository.DishRepository;
import ru.vaseba.myrestaurant.util.ValidationUtil;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = DishController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Dish Controller")
public class DishController {
    static final String URL = "/api/dish";

    private final DishRepository dishRepository;

    @Autowired
    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll() {
        List<Dish> dishes = dishRepository.findAll();
        log.info("get all users: {}", dishes);
        return dishes;
    }

    @GetMapping(value = "/{id}")
    public Optional<Dish> getById(@PathVariable int id) {
        Optional<Dish> dish = dishRepository.findById(id);
        log.info("get Dish by id: {}, user: {}", id, dish);
        return dish;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish) {
        Dish created = dishRepository.save(dish);
        log.info("create user: {}", dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish update(@RequestBody Dish dish, @PathVariable int id) {
        log.info("update dish: {} id: {}, ", dish, id);
        ValidationUtil.assureIdConsistent(dish, id);

        return dishRepository.save(dish);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete dish id: {}", id);
        dishRepository.deleteById(id);
    }
}
