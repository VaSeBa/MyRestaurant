package ru.vaseba.myrestaurant.repository;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.entity.User;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {
    Optional<User> getByEmail(String email);
}