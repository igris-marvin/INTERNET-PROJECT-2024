package com.sanienterprise.dawn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sanienterprise.dawn.api.service.PatronService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private PatronService patServ;

    public SecurityConfiguration(PatronService patServ) {
        this.patServ = patServ;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(t -> {
                t.requestMatchers("/").permitAll();
                t.requestMatchers("/images/**").permitAll();
                t.requestMatchers("/hidden/**").permitAll();
                t.requestMatchers("/browse/**").permitAll();
                t.requestMatchers("/category").permitAll();
                t.requestMatchers("/home/**").permitAll();
                t.requestMatchers("/user/**").hasRole("CUSTOMER");
                t.requestMatchers("/admin/**").hasRole("ADMIN");
                t.anyRequest().authenticated();
        }).logout(t -> {
            t.logoutSuccessUrl("/");
        })
            .formLogin(AbstractAuthenticationFilterConfigurer -> {
                AbstractAuthenticationFilterConfigurer
                    .loginPage("/login")
                    .successHandler(new AuthenticationSuccessHandler())
                    .permitAll();
            })
            .build();
 	}

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails customer = User.builder()
    //         .username("customer")
    //         .password("$2a$12$1tTMKaLHnERTbXTCUNDoH.UH335R4IxkmiKru6O8kg8fNAyDNgRJG")
    //         .roles("CUSTOMER")
    //         .build();

    //     UserDetails admin = User.builder()
    //         .username("admin")
    //         .password("$2a$12$RrHE1GVWd2cMcFtwVyz/0u55ARS1uoRgQAd5DGXp9pok.0GPyqjfu")
    //         .roles("ADMIN", "CUSTOMER")
    //         .build();

    //     return new InMemoryUserDetailsManager(customer, admin);
    // }

    @Bean
    public UserDetailsService userDetailsService() {
        return patServ;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(patServ);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
