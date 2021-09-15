package ru.vaseba.myrestaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vaseba.myrestaurant.model.Restaurant;
import ru.vaseba.myrestaurant.repository.RestaurantRepository;
import ru.vaseba.myrestaurant.to.RestaurantDto;

import java.util.UUID;

@Service
@Slf4j
public class RestaurantService extends BaseEntityService<Restaurant, RestaurantRepository> {

    private static final String RESTAURANTS_CACHE_NAME = "restaurantsWithActualMenus";

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        super(repository);
    }

    @Override
    protected void prepareBeforeCreate(Restaurant entity) {
    }

    @Override
    @CacheEvict(value = RESTAURANTS_CACHE_NAME, allEntries = true)
    public Restaurant create(Restaurant entity) {
        return super.create(entity);
    }

    @Override
    protected void prepareBeforeUpdate(Restaurant entity) {
    }

    @Override
    @CacheEvict(value = RESTAURANTS_CACHE_NAME, allEntries = true)
    public Restaurant update(Restaurant entity) {
        return super.update(entity);
    }

    @Override
    @CacheEvict(value = RESTAURANTS_CACHE_NAME, allEntries = true)
    public void delete(UUID id) {
        super.delete(id);
    }

    @Cacheable(RESTAURANTS_CACHE_NAME)
    public Page<RestaurantDto> findAllWithActualMenus(Pageable pageable) {
        Page<Restaurant> page = repository.findAllWithActualMenus(pageable);
        log.info("Найдено {} ресторана(ов) с меню дня", page.getTotalElements());
        return page.map(RestaurantDto::new);
    }

}
