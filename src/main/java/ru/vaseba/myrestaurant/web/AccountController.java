package ru.vaseba.myrestaurant.web;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vaseba.myrestaurant.AuthUser;
import ru.vaseba.myrestaurant.model.User;

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {
    // GET http://localhost:8080/api/account
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthUser authUser) {
        return authUser.getUser();
    }
}
