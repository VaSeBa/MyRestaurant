package ru.vaseba.myrestaurant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.vaseba.myrestaurant.security.JwtConfigurerAdapter;
import ru.vaseba.myrestaurant.security.JwtTokenProvider;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static ru.vaseba.myrestaurant.model.Role.ADMIN;
import static ru.vaseba.myrestaurant.model.Role.USER;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/swagger-ui.html")
                .antMatchers("/v2/api-docs")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/register/**").permitAll()
                .antMatchers("/api/v1/login/**").permitAll()
                .antMatchers("/api/v1/restaurants/menus/actual/").permitAll()
                .antMatchers("/api/v1/voices/**").hasRole(USER)
                .antMatchers("/api/v1/restaurants/**").hasRole(ADMIN)
                .antMatchers("/api/v1/menus/**").hasRole(ADMIN)
                .antMatchers("/api/v1/users/**").hasRole(ADMIN)
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurerAdapter(jwtTokenProvider));
    }
}
