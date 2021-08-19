package ru.vaseba.myrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Dish;
import ru.vaseba.myrestaurant.model.Restaurant;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

}
