package com.kernel360.boogle.reply.db;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    ReplyEntity save(ReplyEntity reply);

    List<ReplyEntity> findAllByBookReportEntity(BookReportEntity bookReportEntity);

    List<ReplyEntity> findAllByCreatedAtBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    Optional<ReplyEntity> findById(Long replyId);

    Optional<List<ReplyEntity>> findAllByParentReplyId(Long parentReplyId);

    List<ReplyEntity> findAllByMemberEntityIdOrderByCreatedAtDesc(Long MemberId);

    void deleteById(Long id);
}
