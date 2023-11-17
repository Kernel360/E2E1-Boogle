package com.kernel360.boogle.reply.service;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.db.BookReportRepository;
import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.reply.db.ReplyEntity;
import com.kernel360.boogle.reply.db.ReplyRepository;
import com.kernel360.boogle.reply.exception.EmptyContentException;
import com.kernel360.boogle.reply.model.ReplyDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BookReportRepository bookReportRepository;

    public ReplyService(ReplyRepository replyRepository, BookReportRepository bookReportRepository) {
        this.replyRepository = replyRepository;
        this.bookReportRepository = bookReportRepository;
    }

    public void createReply(ReplyDTO reply, @AuthenticationPrincipal MemberEntity member) {

        if (reply.getReplyEntity().getContent().replaceAll("\\s", "").equals("")) {
            throw new EmptyContentException();
        }

        reply.getReplyEntity().setMemberEntity(member);
        replyRepository.save(reply.getReplyEntity());
    }

    public Optional<ReplyEntity> getReplyById(Long id) {
        return replyRepository.findById(id);
    }

    public Optional<List<ReplyDTO>> getRepliesByBookReportId(Long bookReportId) {
        BookReportEntity bookReport =bookReportRepository.findById(bookReportId).get();
        return Optional.of(replyRepository.findAllByBookReportEntity(bookReport)
                .stream()
                .map(ReplyDTO::from)
                .toList());
    }

    public void updateReply(ReplyDTO reply, @AuthenticationPrincipal MemberEntity member) {

        if (reply.getReplyEntity().getContent().replaceAll("\\s", "").equals("")) {
            throw new EmptyContentException();
        }

        reply.getReplyEntity().setMemberEntity(member);
        replyRepository.save(reply.getReplyEntity());
    }

    public void deleteReply(ReplyDTO reply) {
        replyRepository.deleteById(reply.getId());
    }

    public Optional<List<ReplyDTO>> getRecentRepliesByMemberId(Long memberId, int cnt) {
        return Optional.of(replyRepository.findAllByMemberEntityId(memberId)
                .stream()
                .map(ReplyDTO::from)
                .sorted((r1, r2) -> r2.getCreatedAt().compareTo(r1.getCreatedAt()))
                .limit(cnt)
                .toList());
    }
}
