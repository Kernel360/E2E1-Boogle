package com.kernel360.boogle.reply.db;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

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


    @Query(nativeQuery = true, value = "select * from REPLY where member_id = ?1 order by created_at desc limit ?2 ")
    List<ReplyEntity> findAllByMemberId(Long memberId, int cnt);

    void deleteById(Long id);
}
