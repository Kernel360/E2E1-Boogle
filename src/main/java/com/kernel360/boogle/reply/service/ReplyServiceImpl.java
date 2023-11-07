package com.kernel360.boogle.reply.service;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.db.BookReportRepository;
import com.kernel360.boogle.reply.db.ReplyEntity;
import com.kernel360.boogle.reply.db.ReplyRepository;
import com.kernel360.boogle.reply.model.ReplyDTO;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final BookReportRepository bookReportRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository, BookReportRepository bookReportRepository) {
        this.replyRepository = replyRepository;
        this.bookReportRepository = bookReportRepository;
    }

    @Override
    public void createReply(ReplyDTO reply) {
        reply.getReplyEntity().setMemberId(1L); // 로그인 사용자 정보 들어가야 함.
        replyRepository.save(reply.getReplyEntity());
    }

    @Override
    public Optional<List<ReplyEntity>> getRepliesByBookReport(Long bookReportId) {
        Optional<BookReportEntity> bookReport = bookReportRepository.findById(bookReportId);
        if (bookReport.isPresent()) {
            return replyRepository.findReplyEntitiesByBookReportEntityAndIsDeleted(bookReport.get(), "N");
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void updateReply(ReplyDTO reply) {
        replyRepository.save(reply.getReplyEntity());
    }

    @Override
    public void deleteReply(Long replyId) {
        replyRepository.findById(replyId)
                .map(
                        it -> {
                            it.setIsDeleted("Y");
                            it.setDeletedAt(LocalDateTime.now());
                            replyRepository.save(it);
                            return it;
                        }
                );

    }
}
