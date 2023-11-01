package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.model.BookViewRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookService {
    public void createBook(BookDTO book);

    Optional<BookEntity> getBookById(Long bookId);

    Page<BookEntity> getBooks(int page, String searchWord);

    Page<BookEntity> getBooksByAuthor(int page, String searchWord);

    Page<BookEntity> getBooksByTitle(int page, String searchWord);

    Page<BookEntity> getBooksByPublisher(int page, String searchWord) ;

    public void updateBook(BookDTO book);

    public void deleteBook(BookViewRequest bookViewRequest);
}
