package com.kernel360.boogle.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity (name = "BOOK")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookTitle;
    private Long categoryId;
    private Long thumbnailUrl;
    private String author;
    private String publisher;
    private Long isbn;
    private String description;
    private LocalDateTime publishDate;
    private int salesPrice;
}
