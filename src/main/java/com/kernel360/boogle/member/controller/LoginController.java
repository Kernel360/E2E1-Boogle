package com.kernel360.boogle.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@RestController
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login(String errorCode, String logout) {
        return new ModelAndView("login");
    }
}
