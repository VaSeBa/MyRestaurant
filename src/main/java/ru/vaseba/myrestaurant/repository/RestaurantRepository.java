package ru.vaseba.myrestaurant.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Restaurant;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseNamedEntityJpaRepository<Restaurant> {
}
