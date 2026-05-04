package com.example.infraestructure.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.infraestructure.security.auth.UserDetailsServiceImpl;
import com.example.infraestructure.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthenticationFilter jwtFilter)
            throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .httpBasic(httpBasic -> httpBasic.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    http.requestMatchers("/api/auth/**").permitAll();

                    http.requestMatchers(HttpMethod.GET,
                            "/softlearning/books", "/softlearning/books/**",
                            "/softlearning/courses", "/softlearning/courses/**",
                            "/softlearning/vehicles", "/softlearning/vehicles/**").hasAnyRole("ADMIN", "USER", "PRODUCTS_MANAGER");
                    http.requestMatchers(HttpMethod.POST,
                            "/softlearning/books", "/softlearning/books/**",
                            "/softlearning/courses", "/softlearning/courses/**",
                            "/softlearning/vehicles", "/softlearning/vehicles/**").hasAnyRole("ADMIN", "PRODUCTS_MANAGER");
                    http.requestMatchers(HttpMethod.PUT,
                            "/softlearning/books", "/softlearning/books/**",
                            "/softlearning/courses", "/softlearning/courses/**",
                            "/softlearning/vehicles", "/softlearning/vehicles/**").hasAnyRole("ADMIN", "PRODUCTS_MANAGER");
                    http.requestMatchers(HttpMethod.DELETE,
                            "/softlearning/books", "/softlearning/books/**",
                            "/softlearning/courses", "/softlearning/courses/**",
                            "/softlearning/vehicles", "/softlearning/vehicles/**").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/softlearning/clients", "/softlearning/clients/**").hasAnyRole("ADMIN", "USER");
                    http.requestMatchers(HttpMethod.POST, "/softlearning/clients", "/softlearning/clients/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/softlearning/clients", "/softlearning/clients/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/softlearning/clients", "/softlearning/clients/**").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/softlearning/orders", "/softlearning/orders/**").hasAnyRole("ADMIN", "USER");
                    http.requestMatchers(HttpMethod.POST, "/softlearning/orders", "/softlearning/orders/**").hasAnyRole("ADMIN", "USER");
                    http.requestMatchers(HttpMethod.PUT, "/softlearning/orders", "/softlearning/orders/**").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/softlearning/orders", "/softlearning/orders/**").hasRole("ADMIN");

                    http.anyRequest().denyAll();
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
