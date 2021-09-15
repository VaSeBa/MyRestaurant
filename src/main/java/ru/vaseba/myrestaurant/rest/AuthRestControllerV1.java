package ru.vaseba.myrestaurant.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.vaseba.myrestaurant.to.AuthRequestDto;
import ru.vaseba.myrestaurant.to.AuthResponseDto;
import ru.vaseba.myrestaurant.security.JwtTokenProvider;
import ru.vaseba.myrestaurant.service.AuthService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RestResources.V1.Auth.URI, produces = APPLICATION_JSON_VALUE)
public class AuthRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService service;

    @Autowired
    public AuthRestControllerV1(AuthService service, AuthenticationManager authenticationManager,
                                JwtTokenProvider jwtTokenProvider) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Авторизует пользователя", notes = "В результате возвращается JSON-объект с email и токеном")
    @ResponseStatus(CREATED)
    public ResponseEntity<AuthResponseDto> login(@ApiParam(name = "requestBody", required = true)
                                                 @RequestBody AuthRequestDto authRequestDto) {
        String email = authRequestDto.getEmail();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, authRequestDto.getPassword())
        );
        String token = jwtTokenProvider.create(service.loadUserByUsername(email));
        return new ResponseEntity<>(new AuthResponseDto(email, token), CREATED);
    }

}
