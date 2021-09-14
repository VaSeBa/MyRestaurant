package ru.vaseba.myrestaurant.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Voice;

@Transactional(readOnly = true)
public interface VoiceRepository extends BaseEntityJpaRepository<Voice> {
}
