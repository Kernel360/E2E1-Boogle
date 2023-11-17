package com.kernel360.boogle.reply.db;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    ReplyEntity save(ReplyEntity reply);

    List<ReplyEntity> findAllByBookReportEntity(BookReportEntity bookReportEntity);

    Optional<ReplyEntity> findById(Long replyId);

    Optional<List<ReplyEntity>> findAllByParentReplyId(Long parentReplyId);

    List<ReplyEntity> findAllByMemberEntityId(Long MemberId);

    void deleteById(Long id);
}
