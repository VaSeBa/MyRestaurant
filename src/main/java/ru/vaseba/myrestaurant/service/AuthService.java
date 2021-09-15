package ru.vaseba.myrestaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.repository.UserRepository;

@Service
@Slf4j
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable(Cache.User.BY_EMAIL)
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmailWithRoles(email)
                .map(user -> {
                    log.info("Пользователь с email [{}] успешно загружен", email);
                    return user;
                })
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Пользователь с email [%s] не найден", email))
                );
    }

    public User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        } else {
            throw new SessionAuthenticationException("Пользователь не аутентифицирован");
        }
    }

}
