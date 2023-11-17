package com.kernel360.boogle.reply.service;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.db.BookReportRepository;
import com.kernel360.boogle.reply.db.ReplyRepository;
import com.kernel360.boogle.reply.model.ReplyDTO;
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

    public void createReply(ReplyDTO reply) {
        reply.getReplyEntity().setMemberId(1L); // 로그인 사용자 정보 들어가야 함.
        replyRepository.save(reply.getReplyEntity());
    }

    public Optional<List<ReplyDTO>> getRepliesByBookReportId(Long bookReportId) {
        BookReportEntity bookReport =bookReportRepository.findById(bookReportId).get();
        return Optional.of(replyRepository.findAllByBookReportEntity(bookReport)
                .stream()
                .map(ReplyDTO::from)
                .toList());
    }

    public void updateReply(ReplyDTO reply) {
        replyRepository.save(reply.getReplyEntity());
    }

    public void deleteReply(ReplyDTO reply) {
        replyRepository.deleteById(reply.getId());
    }

    public Optional<List<ReplyDTO>> getRecentRepliesByMemberId(Long memberId, int cnt) {
        return Optional.of(replyRepository.findAllByMemberId(memberId)
                .stream()
                .map(ReplyDTO::from)
                .sorted((r1, r2) -> r2.getCreatedAt().compareTo(r1.getCreatedAt()))
                .limit(cnt)
                .toList());
    }
}
