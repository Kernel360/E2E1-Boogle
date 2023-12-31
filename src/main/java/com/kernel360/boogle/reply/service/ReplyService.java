package com.kernel360.boogle.reply.service;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.db.BookReportRepository;
import com.kernel360.boogle.global.error.code.ReplyErrorCode;
import com.kernel360.boogle.global.error.exception.BusinessException;
import com.kernel360.boogle.member.model.MemberDTO;
import com.kernel360.boogle.reply.db.ReplyEntity;
import com.kernel360.boogle.reply.db.ReplyRepository;
import com.kernel360.boogle.reply.model.ReplyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BookReportRepository bookReportRepository;

    @Transactional
    public void createReply(ReplyDTO reply, MemberDTO memberDTO) {

        validateReplyContent(reply);

        reply.getReplyEntity().setMemberEntity(memberDTO.getMemberEntity());
        replyRepository.save(reply.getReplyEntity());
    }
    @Transactional(readOnly = true)
    public Optional<ReplyEntity> getReplyById(Long id) {
        return replyRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ReplyDTO> getRepliesByBookReportId(Long bookReportId) {
        BookReportEntity bookReport = bookReportRepository.findById(bookReportId).get();
        return replyRepository.findAllByBookReportEntity(bookReport)
                .stream()
                .map(ReplyDTO::from)
                .toList();
    }
    @Transactional(readOnly = true)
    public Optional<List<ReplyEntity>> getRepliesByParentReplyId(Long parentReplyId) {
        return replyRepository.findAllByParentReplyId(parentReplyId);
    }

    @Transactional
    public void updateReply(ReplyDTO reply, MemberDTO memberDTO) {

        validateReplyContent(reply);

        reply.getReplyEntity().setMemberEntity(memberDTO.getMemberEntity());
        replyRepository.save(reply.getReplyEntity());
    }

    @Transactional
    public void deleteReply(ReplyDTO reply) {

        replyRepository.deleteById(reply.getId());
    }

    public int getAllRepliesCount(Long memberId) {

        return 5;
    }

    @Transactional(readOnly = true)
    public List<ReplyDTO> getRecentRepliesByMemberId(Long memberId, int cnt) {
        return replyRepository.findAllByMemberId(memberId, cnt)
                .stream()
                .map(ReplyDTO::from)
                .toList();
    }

    private void validateReplyContent(ReplyDTO reply) {
        if (reply.getReplyEntity().getContent().trim().isEmpty()) {
            throw new BusinessException(ReplyErrorCode.EMPTY_CONTENT_REPLY);
        }
    }
}
