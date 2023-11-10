package com.kernel360.boogle.member.controller;

import com.kernel360.boogle.member.exception.AlreadySignedupMemberException;
import com.kernel360.boogle.member.model.MemberSignupDTO;
import com.kernel360.boogle.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final MemberService memberService;

    public SignupController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String signup() {
        return "signup";
    }

    @PostMapping
    public String signup(@ModelAttribute MemberSignupDTO memberSignupDTO, Model model) {
        try {
            memberService.signup(memberSignupDTO);
            return "redirect:login";
        } catch (AlreadySignedupMemberException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }
}
