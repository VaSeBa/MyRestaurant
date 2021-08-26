package ru.vaseba.myrestaurant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.model.Voice;
import ru.vaseba.myrestaurant.repository.VoiceRepository;

import java.util.List;

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


}
