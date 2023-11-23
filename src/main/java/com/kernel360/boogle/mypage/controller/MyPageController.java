package com.kernel360.boogle.mypage.controller;

import com.kernel360.boogle.bookreport.service.BookReportService;
import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.service.MemberService;
import com.kernel360.boogle.mypage.model.MemberRequestDTO;
import com.kernel360.boogle.mypage.service.MyPageService;
import com.kernel360.boogle.reply.service.ReplyService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Api(tags = {"마이페이지 관련 API"})
@RestController
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

    private final BookReportService bookReportService;
    private final ReplyService replyService;
    private final MemberService memberService;
    private final MyPageService myPageService;

    @GetMapping("/mypage/main")
    public ModelAndView getMyPageMain(@AuthenticationPrincipal MemberEntity member) {
        final Long memberId = member.getId();

        return new ModelAndView("mypage/main")
                .addAllObjects(
                        Map.of("member", member,
                                "replies", replyService.getRecentRepliesByMemberId(memberId, 10),
                                "bookReports", bookReportService.getBookReportsByMemberId(memberId))
                );
    }

    @GetMapping("/mypage/memberInfo")
    public ModelAndView getMemberInfo(
            @AuthenticationPrincipal MemberEntity member) {

        return new ModelAndView("mypage/memberInfo")
                .addObject("member", member)
                .addObject("memberRequestDTO", new MemberRequestDTO());
    }


    @PostMapping("/mypage/memberInfo")
    @CrossOrigin
    public ModelAndView updateMemberInfo(
            @AuthenticationPrincipal MemberEntity memberEntity,
            @Valid @ModelAttribute MemberRequestDTO memberRequestDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            log.error("Binding errors: {}", bindingResult.getAllErrors());
            return new ModelAndView("mypage/memberInfo")
                    .addObject("member", memberEntity)
                    .addObject("memberRequestDTO", memberRequestDTO)
                    .addObject("errors", bindingResult.getAllErrors());
        }

        if (!myPageService.validatePassword(memberEntity, memberRequestDTO)) {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
            log.error("비밀번호가 일치하지 않습니다.");
            return new ModelAndView("redirect:/mypage/memberInfo");
        }

        memberService.updateMemberInfo(memberRequestDTO);
        redirectAttributes.addFlashAttribute("message", "회원 정보가 성공적으로 업데이트되었습니다.");
        log.info("회원 정보가 성공적으로 업데이트되었습니다.");
        return new ModelAndView("redirect:/mypage/main");
    }}