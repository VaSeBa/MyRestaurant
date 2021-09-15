package ru.vaseba.myrestaurant.util;

import lombok.NoArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class RestControllerUtils {

    public static URI getUriNewResource(String requestUri, UUID id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(requestUri + "{id}")
                .buildAndExpand(id)
                .toUri();
    }

}
