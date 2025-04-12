package com.example.featureservice.config;

import com.example.featureservice.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF
                // Configure authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/student/**").hasAuthority("STUDENT")
                        .requestMatchers("/faculty/**").hasAuthority("FACULTY")
                        .anyRequest().authenticated()
                )
                // Configure stateless session management for JWT
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Add a JWT-based filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public OncePerRequestFilter jwtAuthenticationFilter() {
//        // Custom JWT filter to validate tokens
//        return new JwtAuthenticationFilter();
//    }
}