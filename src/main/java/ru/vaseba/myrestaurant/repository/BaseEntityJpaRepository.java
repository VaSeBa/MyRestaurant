package ru.vaseba.myrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import ru.vaseba.myrestaurant.model.BaseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseEntityJpaRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {

    @Override
    @Query("SELECT e FROM #{#entityName} e WHERE e.status = 1")
    List<T> findAll();

    @Override
    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND e.status = 1")
    Optional<T> findById(@Param("id") UUID id);

    @Query("SELECT e FROM #{#entityName} e WHERE e.status IN (1, 2)")
    List<T> findAllWithNotActive();

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND e.status IN (1, 2)")
    Optional<T> findByIdWithNotActive(@Param("id") UUID id);

    @Modifying
    @Query("UPDATE #{#entityName} e SET e.status = 0 WHERE e.id = :id")
    void deleteById(@Param("id") UUID id);

    @Modifying
    @Query("UPDATE #{#entityName} e SET e.status = 0")
    void deleteAll();

}
