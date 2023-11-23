package com.kernel360.boogle.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String referrer = request.getHeader("Referer");

        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            String view = "redirect:/" + (referrer != null ? referrer : "books");
            return view;
        }

        return "login";
    }
}
