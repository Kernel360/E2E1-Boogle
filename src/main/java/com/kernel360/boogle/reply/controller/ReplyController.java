package com.kernel360.boogle.reply.controller;

import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.reply.exception.EmptyContentException;
import com.kernel360.boogle.reply.model.ReplyDTO;
import com.kernel360.boogle.reply.service.ReplyService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public void createReply(@RequestBody ReplyDTO reply, @AuthenticationPrincipal MemberEntity member) {
        try {
            replyService.createReply(reply, member);
        } catch (EmptyContentException e) {
            throw e;
        }
    }

    @PatchMapping("/reply")
    public void updateReply(@RequestBody ReplyDTO reply, @AuthenticationPrincipal MemberEntity member) {
        logger.info("댓글 수정이 수행됨. 수정 전 댓글 정보: " + replyService.getReplyById(reply.getReplyEntity().getId()) + " 수정 후 댓글 정보: " + reply.getReplyEntity());
        try {
            replyService.updateReply(reply, member);
        } catch (EmptyContentException e) {
            throw e;
        }
    }

    @DeleteMapping("/reply")
    public void deleteReply(@RequestBody ReplyDTO reply) {
        logger.info("댓글/대댓글 삭제가 수행됨. 삭제된 댓글 정보: " + replyService.getReplyById(reply.getId()) + " 추가로 삭제된 대댓글 정보: " + replyService.getRepliesByParentReplyId(reply.getId()));
        replyService.deleteReply(reply);
    }
}
