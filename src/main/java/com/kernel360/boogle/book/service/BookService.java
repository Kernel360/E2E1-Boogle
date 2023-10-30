package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.model.BookRequest;
import com.kernel360.boogle.book.model.BookViewRequest;
import org.springframework.data.domain.Page;
import java.util.*;

public interface BookService {
    public void createBook(BookDTO book);

    public void updateBook(BookDTO book);

    List<BookDTO> findAladinBook(BookRequest request);

    Optional<BookEntity> findById(Long bookId);

    Page<BookEntity> getAllBooks(int page, String searchWord, String searchType);

    public void deleteBook(BookViewRequest bookViewRequest);
}
