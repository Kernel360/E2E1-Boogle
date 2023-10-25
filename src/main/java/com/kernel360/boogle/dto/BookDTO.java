package com.kernel360.boogle.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookDTO {
    private Long bookId;
    private String bookTitle;
    private String thumbnailUrl;
    private String author;
    private String publisher;
    private LocalDateTime publishDate;
    private int salesPrice;
}
