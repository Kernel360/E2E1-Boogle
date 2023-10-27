package com.kernel360.boogle.book.model;

import com.kernel360.boogle.book.db.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long bookId;
    private String bookTitle;
    private Long categoryId;
    private String thumbnailUrl;
    private String author;
    private String publisher;
    private Long isbn;
    private String description;
    private LocalDateTime publishDate;
    private int salesPrice;
    private String createdBy;
    private String lastModifiedBy;
    private String deletedYn;

    @Transient
    private BookEntity bookEntity;
}
