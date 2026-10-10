package org.example.meeter.auth.securityConfig.securityConfig;


import org.example.meeter.auth.authorization.TokenAuthenticationFilter;
import org.example.meeter.auth.authorization.TokenAuthSuccessHandler;
import org.example.meeter.auth.token.TokenRepository;
import org.example.meeter.auth.userDetailsService.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_ENDPOINTS = {
            "/login",
            "/login/",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            MyUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            TokenRepository tokenRepository,
            TokenAuthSuccessHandler successHandler
    ) throws Exception {

        TokenAuthenticationFilter tokenFilter =
                new TokenAuthenticationFilter(tokenRepository);

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .successHandler(successHandler)
                        .permitAll())
                .authenticationProvider(authenticationProvider(
                        http.getSharedObject(
                                org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder.class
                        ) == null ? null : null,
                        passwordEncoder()
                ))
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
