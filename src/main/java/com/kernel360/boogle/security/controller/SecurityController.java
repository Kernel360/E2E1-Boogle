package com.kernel360.boogle.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@RestController
public class SecurityController {

    @GetMapping("/admin/login")
    public ModelAndView login(String errorCode, String logout) {

        log.info("login get ..........");
        log.info("logout: " + logout);
        if (logout != null) {
            log.info("user logout.........");
        }
        return new ModelAndView("/login");
    }
}
