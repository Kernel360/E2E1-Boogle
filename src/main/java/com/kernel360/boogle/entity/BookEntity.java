package com.kernel360.boogle.entity;


import lombok.*;

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
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "THUMBNAIL_URL")
    private String thumbnailUrl;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "ISBN")
    private Long isbn;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PUBLISH_DATE")
    private LocalDateTime publishDate;

    @Column(name = "SALES_PRICE")
    private int salesPrice;
}
