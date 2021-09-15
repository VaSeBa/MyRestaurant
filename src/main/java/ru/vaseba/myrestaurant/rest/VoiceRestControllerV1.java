package ru.vaseba.myrestaurant.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaseba.myrestaurant.model.Voice;
import ru.vaseba.myrestaurant.service.AuthService;
import ru.vaseba.myrestaurant.service.VoiceService;
import ru.vaseba.myrestaurant.to.VoiceDto;
import ru.vaseba.myrestaurant.to.VoiceRequestDto;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.vaseba.myrestaurant.util.RestControllerUtils.getUriNewResource;


@RestController
@RequestMapping(value = RestResources.V1.Voice.URI, produces = APPLICATION_JSON_VALUE)
public class VoiceRestControllerV1 {

    private final VoiceService service;
    private final AuthService authService;

    @Autowired
    public VoiceRestControllerV1(VoiceService service, AuthService authService) {
        this.service = service;
        this.authService = authService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Выбирает ресторан (его меню) для обеда, требуется авторизация с ролью \"USER\" по \"Bearer_\" ->",
            notes = "В результате возвращается JSON-объект нового голоса"
    )
    @ResponseStatus(CREATED)
    public ResponseEntity<VoiceDto> vote(@ApiParam(name = "requestBody", required = true)
                                         @RequestBody VoiceRequestDto requestDto) {
        Voice voice = service.vote(authService.getAuthUser().getId(), requestDto.getMenuId());
        return ResponseEntity
                .created(getUriNewResource(RestResources.V1.Voice.URI, voice.getId()))
                .body(new VoiceDto(voice));
    }

    @PutMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Изменяет выбор ресторана (его меню) для обеда в тот же день до 11:00, " +
            "требуется авторизация с ролью \"USER\" по \"Bearer_\" ->",
            notes = "В результате возвращается JSON-объект изменённого голоса"
    )
    @ResponseStatus(OK)
    public ResponseEntity<?> changeVote(@ApiParam(required = true, defaultValue = "f16eb5ea-14bc-42b0-809a-333639464730")
                                        @PathVariable UUID id,
                                        @ApiParam(name = "requestBody", required = true) @RequestBody VoiceRequestDto requestDto) {
        try {
            Voice voice = service.changeVote(id, authService.getAuthUser().getId(), requestDto.getMenuId());
            return ResponseEntity.ok(new VoiceDto(voice));
        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (TimeoutException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @GetMapping(RestResources.V1.Voice.MY)
    @ApiOperation(value = "Находит историю выбора ресторанов, требуется авторизация с ролью \"USER\" по \"Bearer_\" ->",
            notes = "В результате возвращается массив JSON-объектов голосов с пагинацией"
    )
    @ResponseStatus(OK)
    public Page<VoiceDto> getMyVoices(Pageable pageable) {
        return service.getAllAllByUserId(authService.getAuthUser().getId(), pageable).map(VoiceDto::new);
    }

}
