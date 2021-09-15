package ru.vaseba.myrestaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.repository.RoleRepository;
import ru.vaseba.myrestaurant.repository.UserRepository;
import ru.vaseba.myrestaurant.to.RegisterRequestDto;
import ru.vaseba.myrestaurant.to.RoleDto;
import ru.vaseba.myrestaurant.to.UserDto;
import java.util.Set;
import java.util.UUID;

import static java.util.Collections.singleton;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toSet;
import static ru.vaseba.myrestaurant.model.Role.USER;


@Service
@Slf4j
public class UserService extends BaseEntityService<User, UserRepository> {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User entityOf(RegisterRequestDto dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public User entityOf(UserDto dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity, "roles");
        if (!dto.getRoles().isEmpty()) {
            if (dto.getRoles().stream().anyMatch(roleDto -> isNull(roleDto.getId()))) {
                enrich(entity, dto.getRoles().stream().map(RoleDto::getName).collect(toSet()));
            } else {
                entity.setRoles(dto.getRoles().stream().map(RoleService::entityOf).collect(toSet()));
            }
        }
        return entity;
    }

    @Override
    @CacheEvict(value = {Cache.User.BY_EMAIL}, allEntries = true)
    public User create(User entity) {
        return super.create(entity);
    }

    @Override
    @CacheEvict(value = {Cache.User.BY_EMAIL}, allEntries = true)
    public User update(UUID id, User entity) {
        return super.update(id, entity);
    }

    @Override
    @CacheEvict(value = {Cache.User.BY_EMAIL}, allEntries = true)
    public void delete(UUID id) {
        super.delete(id);
    }

    private void enrich(User user, Set<String> roles) {
        user.setRoles(roleRepository.findByNameIsIn(roles));
    }

    @Override
    protected void prepareBeforeCreate(User entity) {
        super.prepareBeforeCreate(entity);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setEmail(entity.getEmail().toLowerCase());
    }

    public User register(RegisterRequestDto registerRequestDto) {
        User user = entityOf(registerRequestDto);
        enrich(user, singleton(USER));
        return super.create(user);
    }

}
