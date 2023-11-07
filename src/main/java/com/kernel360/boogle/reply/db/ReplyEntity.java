package com.kernel360.boogle.reply.db;


import com.kernel360.boogle.bookreport.db.BookReportEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "REPLY")
@EntityListeners(AuditingEntityListener.class)
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //    @ManyToOne (멤버 엔티티 생성 이후 주석 해제 필요)
    //    @JoinColumn(name = "member_id", nullable = false)
    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "book_report_id", nullable = false)
    private BookReportEntity bookReportEntity;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "parent_reply_id", updatable = false)
    private Long parentReplyId;

    @ToString.Exclude
    @OrderBy("createdAt ASC")
    @OneToMany(mappedBy = "parentReplyId", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Set<ReplyEntity> childReplies = new LinkedHashSet<>();

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "DATETIME", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_at", columnDefinition = "DATETIME")
    private LocalDateTime lastModifiedAt;

    @Column(name = "is_deleted")
    private String isDeleted = "N";

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
