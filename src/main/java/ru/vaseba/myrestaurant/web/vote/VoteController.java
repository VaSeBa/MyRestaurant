package ru.vaseba.myrestaurant.web.vote;

import io.swagger.v3.oas.annotations.media.Content;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.model.Vote;
import ru.vaseba.myrestaurant.repository.VoteRepository;
import ru.vaseba.myrestaurant.service.VoteService;
import ru.vaseba.myrestaurant.web.AuthUser;
import ru.vaseba.myrestaurant.web.SecurityUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {
    static final String REST_URL = "/api/votes";

    private final VoteRepository repository;
    private final VoteService service;

    @GetMapping
    public List<Vote> getOwn() {
        int authId = SecurityUtil.authId();
        log.info("getOwn for userId={}", authId);
        return repository.getByUserId(authId);
    }

    @GetMapping("/by-date")
    public ResponseEntity<Vote> getOwnByDate(@Nullable @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        int authId = SecurityUtil.authId();
        if (date == null) date = LocalDate.now();
        log.info("getOwnByDate for userId={} and {}", authId, date);
        return ResponseEntity.of(repository.getByUserIdAndDate(authId, date));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)// Needed for CSRF protection
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content)  // https://github.com/springdoc/springdoc-openapi/issues/657#issuecomment-625891941
    public ResponseEntity<Vote> voteToday(@RequestParam int restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        User user = authUser.getUser();
        log.info("voteToday for userId={}", user.id());
        Vote created = service.createToday(user, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/by-date?date={date}")
                .buildAndExpand(created.getActualDate()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revoteToday(@RequestParam int restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        User user = authUser.getUser();
        log.info("re-voteToday for userId={}", user.id());
        service.updateToday(user, restaurantId, false);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteToday(@AuthenticationPrincipal AuthUser authUser) {
        User user = authUser.getUser();
        log.info("deleteToday for userId={}", user.id());
        service.updateToday(user, 0, true);
    }
}