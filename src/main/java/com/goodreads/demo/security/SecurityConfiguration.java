package com.goodreads.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Calendar;
import java.util.Date;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityUserDetailsService securityUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void jwts() {
        String token = Jwts.builder().setSubject("adam")
            .setExpiration(new Date(2050, Calendar.FEBRUARY, 1))
            .setIssuer("info@wstutorial.com")
            .claim("groups", new String[]{"user", "admin"})
            .signWith(SignatureAlgorithm.HS512, "MTIzNDU2Nzg=").compact();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .userDetailsService(securityUserDetailsService)
            .authorizeHttpRequests(requests -> requests
                .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                .requestMatchers("/", "/register").anonymous()
                .anyRequest().authenticated())
//            .formLogin(Customizer.withDefaults())
        ;

        return http.build();
    }
}
