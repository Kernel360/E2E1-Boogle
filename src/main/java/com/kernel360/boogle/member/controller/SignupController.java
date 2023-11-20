package com.kernel360.boogle.member.controller;

import com.kernel360.boogle.global.error.exception.BusinessException;
import com.kernel360.boogle.member.model.MemberSignupDTO;
import com.kernel360.boogle.member.service.MemberService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiResponses({@ApiResponse(code = 409, message = "이미 등록된 유저입니다.")})
    public String signup(@ModelAttribute MemberSignupDTO memberSignupDTO, Model model) {
        try {
            memberService.signup(memberSignupDTO);
            return "redirect:login";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getErrorCode().getMessage());
            return "signup";
        }
    }
}
