package com.kernel360.boogle.dto;


import com.kernel360.boogle.entity.BookEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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

//    public BookEntity toEntity() {
//        BookEntity bookEntity = BookEntity.builder()
//                .bookId(bookId)
//                .bookTitle(bookTitle)
//                .categoryId(categoryId)
//                .thumbnailUrl(thumbnailUrl)
//                .author(author)
//                .publisher(publisher)
//                .isbn(isbn)
//                .description(description)
//                .publishDate(publishDate)
//                .salesPrice(salesPrice)
//                .build();
//        return bookEntity;
//    }
}
