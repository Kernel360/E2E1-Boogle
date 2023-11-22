package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookDTO;
import org.springframework.stereotype.Service;

@Service
public class BookConverter {
    public BookDTO toDto(BookEntity bookEntity) {
        return BookDTO.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .categoryId(bookEntity.getCategoryId())
                .thumbnailUrl(bookEntity.getThumbnailUrl())
                .author(bookEntity.getAuthor())
                .publisher(bookEntity.getPublisher())
                .isbn(bookEntity.getIsbn())
                .description(bookEntity.getDescription())
                .publishDate(bookEntity.getPublishDate())
                .salesPrice(bookEntity.getSalesPrice())
                .createdBy(bookEntity.getCreatedBy())
                .createdAt(bookEntity.getCreatedAt())
                .lastModifiedBy(bookEntity.getLastModifiedBy())
                .lastModifiedAt(bookEntity.getLastModifiedAt())
                .build();
    }
}
