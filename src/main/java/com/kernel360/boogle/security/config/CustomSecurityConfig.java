package com.kernel360.boogle.security.config;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

    private String name;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("----------------configure------" +
                "----------------");

        http.formLogin()
                .loginPage("/admin/login")
                .defaultSuccessUrl("/admin/books")
                .failureUrl("/admin/login");

        http.csrf().disable();

        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("----------------configure----------------------");
        return web -> web.ignoring().requestMatchers(PathRequest.
                toStaticResources().atCommonLocations());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
