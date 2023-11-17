package com.kernel360.boogle.statistics.memberreply.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "MEMBER_REPLY")
public class MemberReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "reply_count", nullable = false)
    private Integer replyCount;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @Column(name = "reply_rank", nullable = false)
    private Integer replyRank;
}
