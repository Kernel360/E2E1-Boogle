package com.kernel360.boogle.bookreport.model;

import com.kernel360.boogle.book.db.BookEntity;
발import com.kernel360.boogle.bookreport.db.BookReportEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookReportDTO {
    private Long id;
    private BookEntity bookEntity;
    private Long memberId;
    //    private MemberEntity memeberEntity;
    private String isPublic;
    private String bookReportTitle;
    private String bookReportContent;
    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;
    private String isDeleted;
    private LocalDateTime deletedAt;
    private BookReportEntity bookReportEntity;
}
