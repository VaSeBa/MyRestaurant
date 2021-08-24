package ru.vaseba.myrestaurant.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vaseba.myrestaurant.repository.VoiceRepository;

@RestController
@RequestMapping(VoiceController.URL)
@Slf4j
@Tag(name = "Voice Controller")
public class VoiceController {
    static final String URL = "/api/voice";

    private final VoiceRepository voiceRepository;

    @Autowired
    public VoiceController(VoiceRepository voiceRepository) {
        this.voiceRepository = voiceRepository;
    }

}
