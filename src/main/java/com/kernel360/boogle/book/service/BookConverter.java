package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookDTO;
import org.springframework.stereotype.Service;

@Service
public class BookConverter {
    public BookDTO toDto(BookEntity bookEntity) {
        return BookDTO.builder()
                .bookId(bookEntity.getBookId())
                .bookTitle(bookEntity.getBookTitle())
                .categoryId(bookEntity.getCategoryId())
                .thumbnailUrl(bookEntity.getThumbnailUrl())
                .author(bookEntity.getAuthor())
                .publisher(bookEntity.getPublisher())
                .isbn(bookEntity.getIsbn())
                .description(bookEntity.getDescription())
                .publishDate(bookEntity.getPublishDate())
                .salesPrice(bookEntity.getSalesPrice())
                .createdBy(bookEntity.getCreatedBy())
                .lastModifiedBy(bookEntity.getLastModifiedBy())
                .deletedYn(bookEntity.getDeletedYn())
                .build();

    }
}
