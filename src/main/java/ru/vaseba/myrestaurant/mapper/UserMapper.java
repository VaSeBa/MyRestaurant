package ru.vaseba.myrestaurant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.to.UserTo;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserTo> {

    @Mapping(target = "email", expression = "java(to.getEmail().toLowerCase())")
    @Override
    User toEntity(UserTo to);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", expression = "java(to.getEmail().toLowerCase())")
    @Override
    User updateFromTo(@MappingTarget User entity, UserTo to);
}
