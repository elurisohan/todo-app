package com.tracknote;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    public class SecurityConfig{

        private final JwtAuthFilter jwtAuthFilter;

        //HttpSecurity is just a object to define rules
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            return http
                    .csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth->auth.requestMatchers("/api/v1/auth/**","/error","/actuator/health").permitAll()
                    .anyRequest().authenticated()
            ).addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class)
                    .build();
        }

        //You configure a UserDetailsService and PasswordEncoder somewhere in your Spring config.
        //Spring uses these to build the AuthenticationManager behind the scenes.
        //You use this method to expose the built AuthenticationManager:
        //@Bean
        //public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        //    return config.getAuthenticationManager();
        //}
        //You inject that manager into your login controller:
        //
        //authenticationManager.authenticate(
        //    new UsernamePasswordAuthenticationToken(username, password)
        //);
        //This:
        //Calls your UserDetailsService.loadUserByUsername(username)
        //Verifies the password
        //Authenticates the user if valid

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
            return config.getAuthenticationManager();
        }
    }

