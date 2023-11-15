package com.kernel360.boogle.mypage.controller;

import com.kernel360.boogle.book.service.BookService;
import com.kernel360.boogle.bookreport.service.BookReportService;
import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.reply.service.ReplyService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Api(tags = {"마이페이지 관련 API"})
@RestController
@RequiredArgsConstructor
public class MyPageController {
    private final BookService bookService;
    private final BookReportService bookReportService;
    private final ReplyService replyService;

    @GetMapping("/mypage/main")
    public ModelAndView getMyPageMain(
            @AuthenticationPrincipal MemberEntity member
            ){
        final Long memberId = member.getId();
        Map<String, Object> model = new HashMap<>();
        model.put("member", member);

        return new ModelAndView("mypage/main")
                .addAllObjects(
                        Map.of("member", model,
                                "replies" , replyService.getRepliesByMemberId(memberId).get(),
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
                .addObject("communityReply", replyService.getRepliesByMemberId(member.getId()));
    }


}
