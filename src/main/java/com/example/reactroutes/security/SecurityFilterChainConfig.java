package com.example.reactroutes.security;

import com.example.reactroutes.jwt.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

        private final AuthenticationProvider authenticationProvider;
        private final JWTAuthenticationFilter jwtAuthenticationFilter;
        private final AuthenticationEntryPoint authenticationEntryPoint;

        public SecurityFilterChainConfig(AuthenticationProvider authenticationProvider,
                        JWTAuthenticationFilter jwtAuthenticationFilter,
                        @Qualifier("delegatedAuthEntryPoint") AuthenticationEntryPoint authenticationEntryPoint) {
                this.authenticationProvider = authenticationProvider;
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
                this.authenticationEntryPoint = authenticationEntryPoint;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf().disable()
                                .cors(Customizer.withDefaults())
                                .authorizeHttpRequests()
                                .requestMatchers("/v2/api-docs",
                                                "/swagger-resources",
                                                "/swagger-resources/**",
                                                "/configuration/ui",
                                                "/configuration/security",
                                                "/swagger-ui.html",
                                                "/webjars/**",
                                                "/v3/api-docs/**",
                                                "/swagger-ui/**")
                                .permitAll()
                                .requestMatchers(
                                                HttpMethod.POST,
                                                "/api/v1/customers",
                                                "/api/v1/auth/login")
                                .permitAll()
                                .requestMatchers(
                                                HttpMethod.GET,
                                                "/ping",
                                                "/api/v1/customers/*/profile-image")
                                .permitAll()
                                .requestMatchers(
                                                HttpMethod.GET,
                                                "/ping")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/actuator/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                                .and()
                                .sessionManagement()
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(
                                                jwtAuthenticationFilter,
                                                UsernamePasswordAuthenticationFilter.class)
                                .exceptionHandling()
                                .authenticationEntryPoint(authenticationEntryPoint);
                return http.build();
        }

}