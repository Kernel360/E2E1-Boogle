package com.kernel360.boogle.reply.model;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.reply.db.ReplyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
    private Long id;
    private MemberEntity memberEntity;
    private BookReportEntity bookReportEntity;
    private String content;
    private Long parentReplyId;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private ReplyEntity replyEntity;

    public static ReplyDTO from(ReplyEntity replyEntity) {
        return new ReplyDTO(
                replyEntity.getId(),
                replyEntity.getMemberEntity(),
                replyEntity.getBookReportEntity(),
                replyEntity.getContent(),
                replyEntity.getParentReplyId(),
                replyEntity.getCreatedAt(),
                replyEntity.getLastModifiedAt(),
                replyEntity
        );
    }
}
