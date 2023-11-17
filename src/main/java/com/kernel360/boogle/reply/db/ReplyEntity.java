package com.kernel360.boogle.reply.db;


import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.member.db.MemberEntity;
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

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name = "book_report_id", nullable = false)
    private BookReportEntity bookReportEntity;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "parent_reply_id", updatable = false)
    private Long parentReplyId;

    @ToString.Exclude
    @OrderBy("createdAt ASC")
    @OneToMany(mappedBy = "parentReplyId", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<ReplyEntity> childReplies = new LinkedHashSet<>();

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "DATETIME", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_at", columnDefinition = "DATETIME")
    private LocalDateTime lastModifiedAt;
}
