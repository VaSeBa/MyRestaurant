package ru.vaseba.myrestaurant.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Voice;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;



@Transactional(readOnly = true)
@Tag(name = "Voice Controller")
public interface VoiceRepository extends BaseRepository<Voice> {

    void getByUserIdAndRestaurantIdAndTimeOfVoicing(Integer user_id, Integer restaurant_id, @NotNull LocalDate timeOfVoicing);
}
