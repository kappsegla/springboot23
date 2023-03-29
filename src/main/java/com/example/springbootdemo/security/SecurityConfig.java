package com.example.springbootdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain filterChainForRestApi(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .securityMatcher("/api/**")
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/messages").hasAuthority("SCOPE_write")
                .requestMatchers(HttpMethod.GET, "/api/persons/*").hasRole("read")
                .requestMatchers(HttpMethod.GET,"/api/orgs/search").permitAll()
                .anyRequest().authenticated()
                .and()
                //.httpBasic()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .ignoringRequestMatchers("/register")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/error").permitAll()
                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                .requestMatchers("/showPersons").authenticated()
                .anyRequest().denyAll()
                .and()
                //.oauth2Login()
                //.defaultSuccessUrl("/showPersons")
                .formLogin()
                .defaultSuccessUrl("/showPersons")
                .and();

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder
                .withJwkSetUri("https://fungover.org/auth/.well-known/jwks.json")
                .jwsAlgorithm(SignatureAlgorithm.ES256)
                .build();
    }
}
