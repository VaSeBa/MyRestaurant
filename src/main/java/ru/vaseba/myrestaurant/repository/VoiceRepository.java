package ru.vaseba.myrestaurant.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.vaseba.myrestaurant.model.Voice;

import java.time.LocalTime;

@Transactional(readOnly = true)
@Tag(name = "Voice Controller")
public interface VoiceRepository extends BaseRepository<Voice> {

    @Query("select v from Voice v where v.user.id = ?1 and v.restaurant.id = ?2 and v.timeOfVoicing = ?3")
    void getByUserIdAndRestaurantIdAndTimeOfVoicing(Integer userId, Integer restaurantId, LocalTime date);

}
