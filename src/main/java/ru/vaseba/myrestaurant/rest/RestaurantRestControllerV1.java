package ru.vaseba.myrestaurant.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaseba.myrestaurant.model.Menu;
import ru.vaseba.myrestaurant.model.Restaurant;
import ru.vaseba.myrestaurant.service.MenuService;
import ru.vaseba.myrestaurant.service.RestaurantService;
import ru.vaseba.myrestaurant.to.MenuDto;
import ru.vaseba.myrestaurant.to.RestaurantDto;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.vaseba.myrestaurant.rest.RestResources.V1.Restaurant.MENUS;
import static ru.vaseba.myrestaurant.util.RestControllerUtils.getUriNewResource;

@RestController
@RequestMapping(value = RestResources.V1.Restaurant.URI, produces = APPLICATION_JSON_VALUE)
public class RestaurantRestControllerV1 {

    private final RestaurantService service;
    private final MenuService menuService;

    @Autowired
    public RestaurantRestControllerV1(RestaurantService service, MenuService menuService) {
        this.service = service;
        this.menuService = menuService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Добавляет новый ресторан, требуется авторизация с ролью \"ADMIN\" по \"Bearer_\" ->",
            notes = "В результате возвращается JSON-объект ресторана"
    )
    @ResponseStatus(CREATED)
    public ResponseEntity<RestaurantDto> create(@ApiParam(name = "requestBody", required = true)
                                                @RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = service.create(service.entityOf(restaurantDto));
        return ResponseEntity
                .created(getUriNewResource(RestResources.V1.Restaurant.URI, restaurant.getId()))
                .body(new RestaurantDto(restaurant));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Находит ресторан", notes = "В результате возвращается JSON-объект ресторана")
    @ResponseStatus(OK)
    public ResponseEntity<RestaurantDto> get(@ApiParam(required = true, defaultValue = "58b3d8b0-7d08-4274-8aa8-68976d0582ee")
                                             @PathVariable UUID id) {
        return ResponseEntity.ok(new RestaurantDto(service.get(id)));
    }

    @GetMapping(MENUS)
    @ApiOperation(value = "Находит все рестораны с их меню",
            notes = "В результате возвращается массив JSON-объектов ресторанов с пагинацией"
    )
    @ResponseStatus(OK)
    public Page<RestaurantDto> getAll(Pageable pageable) {
        return service.getAll(pageable).map(RestaurantDto::new);
    }

    @GetMapping(RestResources.V1.Restaurant.MENUS_ACTUAL)
    @ApiOperation(value = "Находит все рестораны с их меню дня",
            notes = "В результате возвращается массив JSON-объектов ресторанов с пагинацией"
    )
    @ResponseStatus(OK)
    public Page<RestaurantDto> getAllWithActualMenus(Pageable pageable) {
        return service.getAllWithActualMenus(pageable).map(RestaurantDto::new);
    }

    @PostMapping(value = RestResources.V1.Restaurant.ADD_MENU_ACTUAL, consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Добавляет новое меню дня ресторана, требуется авторизация с ролью \"ADMIN\" по \"Bearer_\" ->",
            notes = "В результате возвращается JSON-объект меню"
    )
    @ResponseStatus(CREATED)
    public ResponseEntity<MenuDto> addMenu(@ApiParam(required = true, defaultValue = "58b3d8b0-7d08-4274-8aa8-68976d0582ee")
                                           @PathVariable UUID id,
                                           @ApiParam(name = "requestBody", required = true)
                                           @RequestBody MenuDto menuDto) {
        Menu actualMenu = service.addMenu(id, menuService.entityOf(menuDto));
        return ResponseEntity
                .created(getUriNewResource(RestResources.V1.Menu.URI, actualMenu.getId()))
                .body(new MenuDto(actualMenu));
    }

}
