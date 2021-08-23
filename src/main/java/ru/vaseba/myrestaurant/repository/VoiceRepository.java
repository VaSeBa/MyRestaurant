package ru.vaseba.myrestaurant.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.model.Voice;

@Transactional(readOnly = true)
@Tag(name = "Voice Controller")
public interface VoiceRepository extends BaseRepository<Voice> {
}
