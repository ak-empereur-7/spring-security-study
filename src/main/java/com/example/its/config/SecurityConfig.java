package com.example.its.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // for h2-console
        http.authorizeHttpRequests().antMatchers("/h2-console/**").permitAll()
            .and()
            .csrf()
            .ignoringAntMatchers("/h2-console/**")
            .and()
            .headers().frameOptions().disable();

        http
            .authorizeHttpRequests()
            .mvcMatchers("/login/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
        //        return new Pbkdf2PasswordEncoder();
    }
}
