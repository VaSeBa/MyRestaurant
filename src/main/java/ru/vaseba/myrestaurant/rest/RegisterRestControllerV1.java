package ru.vaseba.myrestaurant.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.service.UserService;
import ru.vaseba.myrestaurant.to.RegisterRequestDto;
import ru.vaseba.myrestaurant.to.UserDto;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.vaseba.myrestaurant.util.RestControllerUtils.getUriNewResource;


@RestController
@RequestMapping(value = RestResources.V1.Register.URI, produces = APPLICATION_JSON_VALUE)
public class RegisterRestControllerV1 {

    private final UserService service;

    @Autowired
    public RegisterRestControllerV1(UserService service) {
        this.service = service;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Регистрирует нового пользователя", notes = "В результате возвращается JSON-объект пользователя")
    @ResponseStatus(CREATED)
    public ResponseEntity<UserDto> register(@ApiParam(name = "requestBody", required = true)
                                            @RequestBody RegisterRequestDto registerRequestDto) {
        User user = service.register(registerRequestDto);
        return ResponseEntity
                .created(getUriNewResource(RestResources.V1.User.URI, user.getId()))
                .body(new UserDto(user));
    }

}
