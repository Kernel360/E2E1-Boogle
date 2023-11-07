package com.kernel360.boogle.reply.controller;

import com.kernel360.boogle.reply.model.ReplyDTO;
import com.kernel360.boogle.reply.model.ReplyResponse;
import com.kernel360.boogle.reply.service.ReplyService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Api(tags = {"댓글 관련 API"})
@RestController
public class ReplyController {
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    private final ReplyService replyService;

    @PostMapping("/reply")
    public void createReply(@RequestBody ReplyDTO reply) {
        replyService.createReply(reply);
    }

    @GetMapping("/book-report-replies")
    public Set<ReplyResponse> getRepliesByBookReport(@RequestParam Long bookReportId) {
        ModelAndView mv = new ModelAndView("replies");
        Optional<List<ReplyDTO>> replyDTOS = replyService.getRepliesByBookReportId(bookReportId);
        return ReplyResponse.organizeChildReplies(replyDTOS.get());
    }

    @PatchMapping("/reply")
    public void updateReply(@RequestBody ReplyDTO reply) {
        replyService.updateReply(reply);
    }

    @PatchMapping("/reply/delete")
    public void deleteReply(@RequestBody ReplyDTO reply) {
        replyService.deleteReply(reply.getId());
    }
}
