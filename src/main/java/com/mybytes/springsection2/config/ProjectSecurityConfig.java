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

        /**
         * Custom configurations as per our requirement
         */
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact").permitAll()) //All these can access without authentication
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // Denying all the requests
    @Profile("prod")
    @Bean
    SecurityFilterChain defaultSecurityFilterChainProd(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests
                        .anyRequest().denyAll()) // Alle Anfragen werden abgelehnt
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))); // Fehlermeldung für unautorisierte Anfragen
        return http.build();
    }

    // Allow all the requests
    @Profile("prod")
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests
                        .anyRequest().permitAll()) // Alle Anfragen werden abgelehnt
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
