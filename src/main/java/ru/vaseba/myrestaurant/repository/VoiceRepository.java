package ru.vaseba.myrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.model.Voice;

@Transactional(readOnly = true)
public interface VoiceRepository extends JpaRepository<Voice, Integer> {
}
