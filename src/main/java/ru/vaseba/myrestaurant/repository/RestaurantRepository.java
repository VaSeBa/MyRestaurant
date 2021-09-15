package ru.vaseba.myrestaurant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Restaurant;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseNamedEntityJpaRepository<Restaurant> {
    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(value = "SELECT r FROM Restaurant r JOIN r.menus m WHERE r.status = 1 AND m.actual = true")
    Page<Restaurant> findAllWithActualMenus(Pageable pageable);

}
