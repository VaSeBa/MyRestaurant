package ru.vaseba.myrestaurant.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.User;

import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
public interface UserRepository extends BaseEntityJpaRepository<User> {

    @EntityGraph(attributePaths = {"roles"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.id = :id AND u.status = 1")
    Optional<User> findByIdWithRoles(@Param("id") UUID id);

    @EntityGraph(attributePaths = {"roles"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.status = 1")
    Optional<User> findByEmailWithRoles(@Param("email") String email);

    @EntityGraph(attributePaths = {"voices"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.id = :id AND u.status = 1")
    Optional<User> findByIdWithVoices(@Param("id") UUID id);

}
