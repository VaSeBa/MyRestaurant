package ru.vaseba.myrestaurant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vaseba.myrestaurant.AuthUser;
import ru.vaseba.myrestaurant.error.AppException;
import ru.vaseba.myrestaurant.error.NotFoundException;
import ru.vaseba.myrestaurant.model.Restaurant;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.model.Voice;
import ru.vaseba.myrestaurant.repository.VoiceRepository;
import ru.vaseba.myrestaurant.to.VoiceTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = VoiceController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Voice Controller")
public class VoiceController {
    static final String URL = "/api/voice";

    private final VoiceRepository voiceRepository;

    @Autowired
    public VoiceController(VoiceRepository voiceRepository) {
        this.voiceRepository = voiceRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Voice> getAll() {
        List<Voice> voices = voiceRepository.findAll();
        log.info("get all voices: {}", voices);
        return voices;
    }

    @PostMapping
    public void voice (@RequestParam("restaurant_id") int restaurantId, @RequestParam("user_id") int userId) {
        voiceRepository.getByUserIdAndRestaurantIdAndTimeOfVoicing(userId, restaurantId, LocalTime.now());
    }

}
