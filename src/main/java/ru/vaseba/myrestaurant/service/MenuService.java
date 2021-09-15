package ru.vaseba.myrestaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaseba.myrestaurant.model.Dish;
import ru.vaseba.myrestaurant.model.Menu;
import ru.vaseba.myrestaurant.repository.MenuRepository;
import ru.vaseba.myrestaurant.to.MenuDto;

import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class MenuService extends BaseEntityService<Menu, MenuRepository> {

    private final DishService dishService;

    @Autowired
    public MenuService(MenuRepository repository, DishService dishService) {
        super(repository);
        this.dishService = dishService;
    }

    public Menu entityOf(MenuDto dto) {
        Menu entity = new Menu();
        BeanUtils.copyProperties(dto, entity, "restaurantId", "dishes");
        entity.setDishes(
                dto.getDishes().stream()
                        .map(dishDto -> {
                            Dish dish = dishService.entityOf(dishDto);
                            dish.setMenu(entity);
                            return dish;
                        })
                        .collect(toList())
        );
        return entity;
    }

    public void makeActual(UUID id, Boolean actual) {
        repository.makeActual(id, actual);
    }

}
