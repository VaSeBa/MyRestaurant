package ru.vaseba.myrestaurant.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaseba.myrestaurant.service.MenuService;
import ru.vaseba.myrestaurant.to.MenuDto;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RestResources.V1.Menu.URI, produces = APPLICATION_JSON_VALUE)
public class MenuRestControllerV1 {

    private final MenuService service;

    @Autowired
    public MenuRestControllerV1(MenuService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Находит меню, требуется авторизация с ролью \"ADMIN\" по \"Bearer_\" ->",
            notes = "В результате возвращается JSON-объект меню"
    )
    @ResponseStatus(OK)
    public ResponseEntity<MenuDto> get(@ApiParam(required = true, defaultValue = "fbb9b1a5-a599-4574-954c-27b52a95be2a")
                                       @PathVariable UUID id) {
        return ResponseEntity.ok(new MenuDto(service.get(id)));
    }

    @PutMapping(value = "{id}")
    @ApiOperation(value = "Делает меню актуальным (меню дня), требуется авторизация с ролью \"ADMIN\" по \"Bearer_\" ->",
            notes = "Запрос не возвращает содержимого"
    )
    @ResponseStatus(NO_CONTENT)
    public void makeActual(@ApiParam(required = true, defaultValue = "fbb9b1a5-a599-4574-954c-27b52a95be2a")
                           @PathVariable UUID id, @RequestParam(defaultValue = "true") Boolean actual) {
        service.makeActual(id, actual);
    }

}
