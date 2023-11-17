package com.kernel360.boogle.mypage.controller;

import com.kernel360.boogle.book.service.BookService;
import com.kernel360.boogle.bookreport.service.BookReportService;
import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.service.MemberService;
import com.kernel360.boogle.mypage.model.MemberRequestDTO;
import com.kernel360.boogle.mypage.service.MyPageService;
import com.kernel360.boogle.reply.service.ReplyService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Api(tags = {"마이페이지 관련 API"})
@RestController
@RequiredArgsConstructor
public class MyPageController {
    private final BookService bookService;
    private final BookReportService bookReportService;
    private final ReplyService replyService;
    private final MemberService memberService;
    private final MyPageService myPageService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/mypage/main")
    public ModelAndView getMyPageMain(
            @AuthenticationPrincipal MemberEntity member
            ){
        final Long memberId = member.getId();

        return new ModelAndView("mypage/main")
                .addAllObjects(
                        Map.of("member", member,
                                "replies" , replyService.getRecentRepliesByMemberId(memberId,10).get(),
                                "bookreports", bookReportService.getBookReportsByMemberId(memberId).get())
                );
    }
    @GetMapping("/mypage/memberInfo")
    public ModelAndView getMemberInfo(
            @AuthenticationPrincipal MemberEntity member){

        return new ModelAndView("mypage/memberInfo" )
                .addObject("member", member);
    }

    @GetMapping("/mypage/book-report")
    public ModelAndView getBookReport(
            @AuthenticationPrincipal MemberEntity member
    ){
        return new ModelAndView("mypage/bookReport")
                .addObject("bookReport", bookReportService.getBookReportsByMemberId(member.getId()));
    }

    @GetMapping("/mypage/communityReply")
    public ModelAndView getCommunityReply(
            @AuthenticationPrincipal MemberEntity member
    ){
        return new ModelAndView("/mypage/communityReply")
                .addObject("communityReply", replyService.getRecentRepliesByMemberId(member.getId(),3));
    }

    @PostMapping("/mypage/memberInfo")
    public ModelAndView updateMemberInfo(
            @AuthenticationPrincipal MemberEntity memberEntity,
            @ModelAttribute MemberRequestDTO member,
            RedirectAttributes redirectAttributes
    ){
        ModelAndView modelAndView = new ModelAndView();

        if (myPageService.checkPassword(memberEntity, member)) {
            memberService.updateMemberInfo(member);
            redirectAttributes.addFlashAttribute("message", "회원 정보가 성공적으로 업데이트되었습니다.");
            modelAndView.setViewName("redirect:/mypage/main");
            logger.info("회원 정보가 성공적으로 업데이트되었습니다.");
            return modelAndView;
        }

        redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
        modelAndView.setViewName("redirect:/mypage/memberInfo");
        logger.error("비밀번호가 일치하지 않습니다.");

        return modelAndView;
    }

}
