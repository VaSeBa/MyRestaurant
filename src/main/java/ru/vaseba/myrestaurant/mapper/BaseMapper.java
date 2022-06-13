package ru.vaseba.myrestaurant.mapper;

import org.mapstruct.MappingTarget;
import ru.vaseba.myrestaurant.entity.BaseEntity;
import ru.vaseba.myrestaurant.to.BaseTo;

import java.util.Collection;
import java.util.List;

public interface BaseMapper<E extends BaseEntity, T extends BaseTo> {

    E toEntity(T to);

    List<E> toEntityList(Collection<T> tos);

    E updateFromTo(@MappingTarget E entity, T to);

    T toTo(E entity);

    List<T> toToList(Collection<E> entities);
}
