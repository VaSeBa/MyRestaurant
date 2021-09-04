package ru.vaseba.myrestaurant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vaseba.myrestaurant.error.DeadLineForVoicingException;
import ru.vaseba.myrestaurant.error.IllegalRequestDataException;
import ru.vaseba.myrestaurant.model.Restaurant;
import ru.vaseba.myrestaurant.model.Voice;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;
import ru.vaseba.myrestaurant.repository.UserRepository;
import ru.vaseba.myrestaurant.repository.VoiceRepository;
import ru.vaseba.myrestaurant.util.SecurityUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.vaseba.myrestaurant.util.ValidationUtil.assureIdConsistent;
import static ru.vaseba.myrestaurant.util.ValidationUtil.checkNew;


@Slf4j
@RestController
@Tag(name = "Voice Controller")
public class VoiceController {
    static final String GET_ALL = "/api/voices";
    static final String VOICING = "/api/user/{userId}/restaurants/{restaurantsId}/voices";
    static final String CREATE_VOICE = "/api/voices";
    static final String UPDATE_VOICE = "/api/voices/{voiceId}";

    private static final LocalTime DEAD_LINE_FOR_VOICING = LocalTime.of(11, 0);

    private final VoiceRepository voiceRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Autowired
    public VoiceController(VoiceRepository voiceRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.voiceRepository = voiceRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = VoiceController.GET_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Voice> getAll() {
        List<Voice> voices = voiceRepository.findAll();
        log.info("get all voices: {}", voices);
        return voices;
    }

//    @PostMapping(value = VoiceController.VOICING, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public void voicing(@PathVariable int restaurantsId, LocalTime localTime, @PathVariable int userId) {
//        Voice newVoice = new Voice(null,
//                                LocalDate.now(),
//                                LocalTime.now(),
//                                userRepository.getById(userId),
//                                restaurantRepository.getById(restaurantsId));
//        if (localTime.isBefore(DEAD_LINE_FOR_VOICING)){
//            voiceRepository.save(newVoice);
//        } else {
//            throw new DeadLineForVoicingException("try voicing tomorrow");
//        }
//    }

    @PostMapping(value = VoiceController.CREATE_VOICE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Voice> addVoice(@RequestBody Voice voice) {
        checkNew(voice);
        Voice created = voiceRepository.save(voice);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(UPDATE_VOICE + "/{voiceId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping(value = VoiceController.UPDATE_VOICE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVoice(@RequestBody Voice voice, @PathVariable int voiceId) {
        assureIdConsistent(voice, voiceId);
        if (voice.getTime().isBefore(DEAD_LINE_FOR_VOICING)){
            addVoice(voice);
        } else {
            throw new DeadLineForVoicingException("try voicing tomorrow, before 11-00");
        }
    }
}
