package com.kernel360.boogle.bookreport.db;

import com.kernel360.boogle.book.db.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "BOOK_REPORT")
@EntityListeners(AuditingEntityListener.class)
public class BookReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity bookEntity;

//    @ManyToOne (멤버 엔티티 생성 이후 주석 해제 필요)
//    @JoinColumn(name = "member_id", nullable = false)
    @Column(name = "member_id")
    private Long memberId;
//    private MemberEntity memeberEntity;

    @Column(name = "is_public", nullable = false)
    private String isPublic;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "DATETIME", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_at", columnDefinition = "DATETIME")
    private LocalDateTime lastModifiedAt;

    @Column(name = "is_deleted")
    private String isDeleted = "N";

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
