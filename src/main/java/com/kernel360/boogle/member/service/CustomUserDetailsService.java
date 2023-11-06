//package com.kernel360.boogle.member.service;
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Log4j2
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private PasswordEncoder passwordEncoder;
//
//    public CustomUserDetailsService(){
//        this.passwordEncoder = new BCryptPasswordEncoder();
//    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("loadUserByUsername: " + username);
//
//        return User.builder().username("admin")
////                .password("1111")
//                .password(passwordEncoder.encode("admin1234!"))
//                .authorities("ROLE_USER")
//                .build();
//    }
//}
