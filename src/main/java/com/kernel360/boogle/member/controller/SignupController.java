package com.kernel360.boogle.member.controller;

import com.kernel360.boogle.global.error.exception.BusinessException;
import com.kernel360.boogle.member.model.MemberSignupDTO;
import com.kernel360.boogle.member.service.MemberService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignupController {

    private final MemberService memberService;

    @GetMapping
    public String signup(Model model) {
        model.addAttribute("memberSignupDTO", new MemberSignupDTO());
        return "signup";
    }

    @PostMapping
    @ApiResponses({@ApiResponse(code = 409, message = "이미 등록된 유저입니다.")})
    public String signup(@Valid @ModelAttribute MemberSignupDTO memberSignupDTO,
                         BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "signup";
        }

        try {
            memberService.signup(memberSignupDTO);
            return "redirect:login";
        } catch (BusinessException e) {
            model.addAttribute("duplicatedError", e.getErrorCode().getMessage());
            return "signup";
        }
    }
}
