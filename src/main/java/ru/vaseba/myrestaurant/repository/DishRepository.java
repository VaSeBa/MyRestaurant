package ru.vaseba.myrestaurant.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Dish;

@Transactional(readOnly = true)
public interface DishRepository extends BaseNamedEntityJpaRepository<Dish> {


}
