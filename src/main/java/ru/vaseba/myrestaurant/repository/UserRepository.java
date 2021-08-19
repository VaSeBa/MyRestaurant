package ru.vaseba.myrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vaseba.myrestaurant.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}