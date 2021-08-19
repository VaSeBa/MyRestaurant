package ru.vaseba.myrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vaseba.myrestaurant.model.Dish;
import ru.vaseba.myrestaurant.model.Restaurant;

public interface DishRepository extends JpaRepository<Dish, Integer> {

}
