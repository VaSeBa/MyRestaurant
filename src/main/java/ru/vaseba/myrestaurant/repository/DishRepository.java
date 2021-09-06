package ru.vaseba.myrestaurant.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Dish;

import java.util.List;

@Transactional(readOnly = true)
@Tag(name = "Dish Controller")
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :restaurantId")
    List<Dish> getAllByRestaurant(@Param("restaurantId") int restaurantsId);

}
