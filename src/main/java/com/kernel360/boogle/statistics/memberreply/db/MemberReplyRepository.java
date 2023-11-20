package com.kernel360.boogle.statistics.memberreply.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MemberReplyRepository extends JpaRepository<MemberReplyEntity, Long> {
    MemberReplyEntity save(MemberReplyEntity memberReply);
    List<MemberReplyEntity> findByRecordDateAndReplyRankBetween(LocalDate recordDate, int minRank, int maxRank);
}
