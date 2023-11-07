package com.kernel360.boogle.reply.controller;

import com.kernel360.boogle.reply.db.ReplyEntity;
import com.kernel360.boogle.reply.model.ReplyDTO;
import com.kernel360.boogle.reply.service.ReplyService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

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

    // 임시 조회 API
    @GetMapping("/book-report-replies")
    @ResponseBody
    public ResponseEntity<List<ReplyEntity>> getRepliesByBookReport(@RequestParam Long bookReportId) {
        Optional<List<ReplyEntity>> replies = replyService.getRepliesByBookReport(bookReportId);
        if (replies.isPresent()) {
            return new ResponseEntity<>(replies.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
