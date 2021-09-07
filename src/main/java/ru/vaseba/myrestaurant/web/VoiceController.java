package ru.vaseba.myrestaurant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vaseba.myrestaurant.AuthUser;
import ru.vaseba.myrestaurant.model.Restaurant;
import ru.vaseba.myrestaurant.model.Voice;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;
import ru.vaseba.myrestaurant.repository.VoiceRepository;
import ru.vaseba.myrestaurant.util.SecurityUtil;
import ru.vaseba.myrestaurant.util.TimeUtil;

import java.net.URI;
import java.time.LocalDate;


@Slf4j
@RestController
@Tag(name = "Voice Controller")
@RequestMapping(value = VoiceController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoiceController {
    static final String URL = "/api/restaurants/voices";
    static final String CREATE = "/api/restaurants/{restaurantsId}/voices/{voicesId}";

    private final VoiceRepository voiceRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public VoiceController(VoiceRepository voiceRepository, RestaurantRepository restaurantRepository) {
        this.voiceRepository = voiceRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public Voice getVoiceByUserIdAndDate() {
        return voiceRepository.getVoiceByDateAndUserId(LocalDate.now(), SecurityUtil.authUserId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Voice> create(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        Voice created = voiceRepository.getVoiceByDateAndUserId(LocalDate.now(), authUser.id());
        if (created != null) {
            created.setRestaurant(restaurant);
        } else {
            created = voiceRepository.save(new Voice(null, TimeUtil.getDeadLineForVoice(), authUser.getUser(), restaurant));
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CREATE)
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).body(created);
    }
}
