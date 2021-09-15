package ru.vaseba.myrestaurant.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ru.vaseba.myrestaurant.model.User;
import ru.vaseba.myrestaurant.service.AuthService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expired}")
    private long msExpired;

    private final AuthService authService;

    @Autowired
    public JwtTokenProvider(AuthService authService) {
        this.authService = authService;
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String create(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()));
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + msExpired))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String resolve(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if (nonNull(header) && header.startsWith("Bearer_")) {
            return header.substring(7);
        }
        return null;
    }

    public boolean validate(String token) {
        try {
            return Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new BadCredentialsException("Токен истек или недействителен");
        }
    }

    public Authentication authenticate(String token) {
        String email = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        User user = authService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

}
