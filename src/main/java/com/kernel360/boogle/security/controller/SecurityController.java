package com.kernel360.boogle.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SecurityController {

    @GetMapping("/admin/login")
    public ModelAndView login() {
        return new ModelAndView("/book/admin/book-login");
    }
}
