package ru.vaseba.myrestaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.repository.RoleRepository;
import ru.vaseba.myrestaurant.repository.UserRepository;
import ru.vaseba.myrestaurant.to.UserDto;

import java.util.Set;

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

    @Override
    protected void prepareBeforeCreate(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setEmail(entity.getEmail().toLowerCase());
    }

    @Override
    protected void prepareBeforeUpdate(User entity) {
    }

    public UserDto register(User user) {
        roleRepository.findByName("USER").ifPresent(role -> user.setRoles(Set.of(role)));
        return new UserDto(super.create(user));
    }

}
