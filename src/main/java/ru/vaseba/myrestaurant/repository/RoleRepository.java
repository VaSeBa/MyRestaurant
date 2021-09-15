package ru.vaseba.myrestaurant.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Role;

import java.util.Set;

@Transactional(readOnly = true)
public interface RoleRepository extends BaseEntityJpaRepository<Role> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.name IN (:names) AND e.status = 1")
    Set<Role> findByNameIsIn(@Param("names") Set<String> names);

    @Query("SELECT e FROM #{#entityName} e WHERE e.name IN (:names) AND e.status IN (1, 2)")
    Set<Role> findByNameIsInWithNotActive(@Param("names") Set<String> names);

}
