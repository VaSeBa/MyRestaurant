package ru.vaseba.myrestaurant.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.service.UserService;
import ru.vaseba.myrestaurant.to.UserDto;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RestResources.V1.User.URI, produces = APPLICATION_JSON_VALUE)
public class UserRestControllerV1 {

    private final UserService service;

    @Autowired
    public UserRestControllerV1(UserService service) {
        this.service = service;
    }

    @PutMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Обновляет существующего пользователя, требуется авторизация с ролью \"ADMIN\" по \"Bearer_\" ->",
            notes = "В результате возвращается JSON-объект пользователя"
    )
    @ResponseStatus(OK)
    public ResponseEntity<UserDto> update(@ApiParam(required = true, defaultValue = "f16eb5ea-14bc-42b0-809a-333639464730")
                                          @PathVariable("id") UUID id, @RequestBody UserDto userDto) {
        User updatedUser = service.update(id, service.entityOf(userDto));
        return ResponseEntity.ok(new UserDto(updatedUser));
    }

}
