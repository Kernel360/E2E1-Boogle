package com.kernel360.boogle.bookreport.model;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.member.db.MemberEntity;
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
    private MemberEntity memberEntity;
    private String isPublic;
    private String title;
    private String content;
    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;
    private BookReportEntity bookReportEntity;
}
