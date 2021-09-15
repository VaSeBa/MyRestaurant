package ru.vaseba.myrestaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vaseba.myrestaurant.model.Menu;
import ru.vaseba.myrestaurant.model.Restaurant;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;
import ru.vaseba.myrestaurant.to.RestaurantDto;

import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class RestaurantService extends BaseEntityService<Restaurant, RestaurantRepository> {

    private final MenuService menuService;

    @Autowired
    public RestaurantService(RestaurantRepository repository, MenuService menuService) {
        super(repository);
        this.menuService = menuService;
    }

    public Restaurant entityOf(RestaurantDto dto) {
        Restaurant entity = new Restaurant();
        BeanUtils.copyProperties(dto, entity, "menus");
        entity.setMenus(
                dto.getMenus().stream()
                        .map(menuDto -> {
                            Menu menu = menuService.entityOf(menuDto);
                            menu.setRestaurant(entity);
                            return menu;
                        })
                        .collect(toList())
        );
        return entity;
    }

    @Override
    @CacheEvict(value = {Cache.Restaurant.ALL, Cache.Restaurant.ALL_WITH_ACTUAL_MENUS}, allEntries = true)
    public Restaurant create(Restaurant entity) {
        return super.create(entity);
    }

    @Cacheable(Cache.Restaurant.ALL)
    public Page<Restaurant> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Cacheable(Cache.Restaurant.ALL_WITH_ACTUAL_MENUS)
    public Page<Restaurant> getAllWithActualMenus(Pageable pageable) {
        Page<Restaurant> page = repository.findAllWithActualMenus(pageable);
        log.info("Найдено {} ресторана(ов) с меню дня", page.getTotalElements());
        return page;
    }

    @Override
    @CacheEvict(value = {Cache.Restaurant.ALL, Cache.Restaurant.ALL_WITH_ACTUAL_MENUS}, allEntries = true)
    public Restaurant update(UUID id, Restaurant entity) {
        return super.update(id, entity);
    }

    @Override
    @CacheEvict(value = {Cache.Restaurant.ALL, Cache.Restaurant.ALL_WITH_ACTUAL_MENUS}, allEntries = true)
    public void delete(UUID id) {
        super.delete(id);
    }

    @CacheEvict(value = {Cache.Restaurant.ALL, Cache.Restaurant.ALL_WITH_ACTUAL_MENUS}, allEntries = true)
    public Menu addMenu(UUID id, Menu menu) {
        menu.setRestaurant(repository.getOne(id));
        return menuService.create(menu);
    }

}
