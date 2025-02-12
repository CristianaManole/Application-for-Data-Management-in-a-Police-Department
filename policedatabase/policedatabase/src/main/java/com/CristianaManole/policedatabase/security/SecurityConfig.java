package com.CristianaManole.policedatabase.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/persoane-cetatenii/delete-choose") // Excludem această rută de la protecția CSRF
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/", "/css/**", "/js/**", "/cases/**").permitAll() // Permitem accesul la paginile publice
                        .requestMatchers("/persoane-cetatenii/delete-choose").authenticated() // Ruta pentru ștergere necesită autentificare
                        .anyRequest().authenticated() // Toate celelalte rute necesită autentificare
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true) // Redirecționează utilizatorul după login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL-ul pentru logout
                        .logoutSuccessUrl("/login") // Redirecționează utilizatorul după logout
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("sectia9") // Username personalizat
                .password(passwordEncoder.encode("politia2025")) // Parola personalizată
                .roles("USER") // Rolul utilizatorului
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
