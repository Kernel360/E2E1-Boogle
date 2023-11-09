package com.kernel360.boogle.reply.controller;

import com.kernel360.boogle.reply.model.ReplyDTO;
import com.kernel360.boogle.reply.service.ReplyService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"댓글 관련 API"})
@RestController
public class ReplyController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    private final ReplyService replyService;

    @PostMapping("/reply")
    public void createReply(@RequestBody ReplyDTO reply) {
        replyService.createReply(reply);
    }

//    @GetMapping("/book-report-replies")
//    public Set<ReplyResponse> getRepliesByBookReport(@RequestParam Long bookReportId) {
//        ModelAndView mv = new ModelAndView("replies");
//        Optional<List<ReplyDTO>> repbookReportIdlyDTOS = replyService.getRepliesByBookReportId(bookReportId);
//        return ReplyResponse.organizeChildReplies(replyDTOS.get());
//    }

    @PatchMapping("/reply")
    public void updateReply(@RequestBody ReplyDTO reply) {
        replyService.updateReply(reply);
    }

    @DeleteMapping("/reply")
    public void deleteReply(@RequestBody ReplyDTO reply) {
        replyService.deleteReply(reply);
        logger.info("댓글 삭제가 정상적으로 수행됨.");
    }
}
