package com.kernel360.boogle.global.config;

import com.kernel360.boogle.global.jwt.JwtAuthenticationFilter;
import com.kernel360.boogle.global.jwt.JwtAuthorizationFilter;
import com.kernel360.boogle.global.jwt.JwtProperties;
import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.service.MemberService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    public SpringSecurityConfig(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().disable(); // basic authentication filter 비활성화
        http.csrf().disable();
        http.rememberMe().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // jwt filter
        http.addFilterBefore(
                new JwtAuthenticationFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class
        ).addFilterBefore(
                new JwtAuthorizationFilter(memberService),
                BasicAuthenticationFilter.class
        );

        http.authorizeRequests()
                .antMatchers("/", "/book-reports", "/signup", "/login", "/books").permitAll()
                .antMatchers("/mypage/**" ).hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/book", "/book-report", "/my-book-reports", "/reply").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/books")
                .permitAll(); // 모두 허용

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/books")
                .invalidateHttpSession(true)
                .deleteCookies(JwtProperties.COOKIE_NAME)
                .deleteCookies(JwtProperties.REFRESH_COOKIE_NAME);
    }

    @Override
    public void configure(WebSecurity web) {
        // 정적 리소스 spring security 대상에서 제외
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return email -> {
            MemberEntity member = memberService.findByEmail(email);
            if (member == null) {
                throw new UsernameNotFoundException(email);
            }
            return member;
        };
    }


}
