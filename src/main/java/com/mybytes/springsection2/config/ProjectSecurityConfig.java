package com.mybytes.springsection2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableWebSecurity
@Configuration
public class ProjectSecurityConfig {

    @Profile("development")
    @Bean
    SecurityFilterChain defaultSecurityFilterChainDev(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact").permitAll()) //All these can access without authentication
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // Allow all Requests
    @Profile("development")
    @Bean
    SecurityFilterChain defaultSecurityFilterChainAllowAll(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // Deny all Requests
    @Profile("development")
    @Bean
    SecurityFilterChain defaultSecurityFilterChainDenyAll(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) -> requests
                        .anyRequest().denyAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
