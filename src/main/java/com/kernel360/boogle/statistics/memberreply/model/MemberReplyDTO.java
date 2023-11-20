package com.kernel360.boogle.statistics.memberreply.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class MemberReplyDTO {
    private Long memberId;
    private int replyCount;
    private LocalDate recordDate;
    private int rank;
}
