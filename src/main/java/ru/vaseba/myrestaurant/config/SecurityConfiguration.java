package ru.vaseba.myrestaurant.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.vaseba.myrestaurant.model.Role;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.repository.UserRepository;
import ru.vaseba.myrestaurant.util.validation.AdminRestaurantsUtil;
import ru.vaseba.myrestaurant.web.AuthUser;
import ru.vaseba.myrestaurant.web.SecurityUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@Slf4j
@AllArgsConstructor
//https://stackoverflow.com/questions/72493425/548473
public class SecurityConfiguration {
    public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PASSWORD_ENCODER;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            log.debug("Authenticating '{}'", email);
            Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
            return new AuthUser(optionalUser.orElseThrow(
                    () -> new UsernameNotFoundException("User '" + email + "' was not found")));
        };
    }

    @Bean
    public OncePerRequestFilter restaurantsAdminFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                AuthUser authUser = SecurityUtil.safeGet();
                if (authUser != null && authUser.hasRole(Role.R_ADMIN)) {
                    AdminRestaurantsUtil.checkRequestAccess(authUser.getUser(), request.getRequestURI());
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/restaurants/**").permitAll()
                .antMatchers(AdminRestaurantsUtil.REST_URL + "/**").hasAnyRole(Role.R_ADMIN.name(), Role.ADMIN.name())
                .antMatchers("/api/admin/**").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/profile").anonymous()
                .antMatchers("/api/**").authenticated()
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
        return http.build();
    }
}