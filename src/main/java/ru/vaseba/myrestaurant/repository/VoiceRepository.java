package ru.vaseba.myrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.model.Voice;

public interface VoiceRepository extends JpaRepository<Voice, Integer> {
}
