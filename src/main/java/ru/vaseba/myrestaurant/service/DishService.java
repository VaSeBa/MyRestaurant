package ru.vaseba.myrestaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaseba.myrestaurant.model.Dish;
import ru.vaseba.myrestaurant.repository.DishRepository;
import ru.vaseba.myrestaurant.to.DishDto;


@Service
@Slf4j
public class DishService extends BaseEntityService<Dish, DishRepository> {

    @Autowired
    public DishService(DishRepository repository) {
        super(repository);
    }

    public Dish entityOf(DishDto dto) {
        Dish entity = new Dish();
        BeanUtils.copyProperties(dto, entity, "menuId");
        return entity;
    }

}
