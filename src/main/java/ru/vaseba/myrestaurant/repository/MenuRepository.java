package ru.vaseba.myrestaurant.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Menu;

import java.util.UUID;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseEntityJpaRepository<Menu> {

    @Transactional
    @Modifying
    @Query("UPDATE Menu m SET m.actual = :actual WHERE m.id = :id AND (m.status = 1 OR  m.status = 2)")
    void makeActual(@Param("id") UUID id, @Param("actual") Boolean actual);

}
