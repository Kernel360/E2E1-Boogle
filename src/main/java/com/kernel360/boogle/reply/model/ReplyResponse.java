package com.kernel360.boogle.reply.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public record ReplyResponse(
        Long id,
        Long memberId,
        Long bookReportId,
        String bookReportCreatedBy,
        String content,
        Long parentReplyId,
        Set<ReplyResponse> childReplies,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt
) {
    private static ReplyResponse of(Long id, Long memberId, Long bookReportId, String bookReportCreatedBy, String content, Long parentReplyId, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        Comparator<ReplyResponse> childRepliesComparator = Comparator
                .comparing(ReplyResponse::createdAt)
                .thenComparingLong(ReplyResponse::id);
        return new ReplyResponse(id, memberId, bookReportId, bookReportCreatedBy, content, parentReplyId, new TreeSet<>(childRepliesComparator), createdAt, lastModifiedAt);
    }

    public static ReplyResponse from(ReplyDTO replyDTO) {
        Long bookReportId = replyDTO.getBookReportEntity().getId();
        String bookReportCreatedBy = replyDTO.getBookReportEntity().getCreatedBy();

        return ReplyResponse.of(
                replyDTO.getId(),
                replyDTO.getMemberId(),
                bookReportId,
                bookReportCreatedBy,
                replyDTO.getContent(),
                replyDTO.getParentReplyId(),
                replyDTO.getCreatedAt(),
                replyDTO.getLastModifiedAt()
        );
    }

    public static Set<ReplyResponse> organizeChildReplies(List<ReplyDTO> dtos) {
        Map<Long, ReplyResponse> map = dtos.stream()
                .map(ReplyResponse::from)
                .collect(Collectors.toMap(ReplyResponse::id, Function.identity()));

        map.values().stream()
                .filter(ReplyResponse::hasParentReply)
                .forEach(replyResponse -> {
                    ReplyResponse parentReply = map.get(replyResponse.parentReplyId());
                    parentReply.childReplies().add(replyResponse);
                });

        return map.values().stream()
                .filter(replyResponse -> !replyResponse.hasParentReply())
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator
                                .comparing(ReplyResponse::createdAt)
                                .reversed()
                                .thenComparingLong(ReplyResponse::id)
                        )
                ));
    }

    public boolean hasParentReply() {
        return parentReplyId != null;
    }
}
