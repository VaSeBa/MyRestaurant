package ru.vaseba.myrestaurant.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Restaurant;


@Transactional(readOnly = true)
@Tag(name = "Restaurant Controller")
public interface RestaurantRepository extends BaseRepository<Restaurant> {

}
