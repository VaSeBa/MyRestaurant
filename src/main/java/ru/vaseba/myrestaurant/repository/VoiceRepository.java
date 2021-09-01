package ru.vaseba.myrestaurant.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Restaurant;
import ru.vaseba.myrestaurant.model.Voice;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;


@Transactional(readOnly = true)
@Tag(name = "Voice Controller")
public interface VoiceRepository extends JpaRepository<Voice, Integer> {


}
