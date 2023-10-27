package com.kernel360.boogle.book.db;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "BOOK")
public class BookEntity extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "isbn")
    private Long isbn;

    @Column(name = "description")
    private String description;

    @Column(name = "publish_date")
    private LocalDateTime publishDate;

    @Column(name = "sales_price")
    private int salesPrice;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "deleted_yn")
    private String deletedYn = "N";
}
