package com.kernel360.boogle.global.config;

import com.kernel360.boogle.global.jwt.JwtAuthenticationFilter;
import com.kernel360.boogle.global.jwt.JwtAuthorizationFilter;
import com.kernel360.boogle.global.jwt.JwtProperties;
import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.db.MemberRepository;
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
    private final MemberRepository memberRepository;

    public SpringSecurityConfig(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
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
                new JwtAuthorizationFilter(memberRepository),
                BasicAuthenticationFilter.class
        );

        http.authorizeRequests()
                .antMatchers("/", "/book-reports", "/signup", "/login", "/book-report").permitAll()
                .antMatchers("/my-book-reports", "/reply").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/book-reports")
                .permitAll(); // 모두 허용

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/book-reports")
                .invalidateHttpSession(true)
                .deleteCookies(JwtProperties.COOKIE_NAME);
    }

    @Override
    public void configure(WebSecurity web) {
        // 정적 리소스 spring security 대상에서 제외
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            MemberEntity member = memberService.findByEmail(username);
            if (member == null) {
                throw new UsernameNotFoundException(username);
            }
            return member;
        };
    }


}
