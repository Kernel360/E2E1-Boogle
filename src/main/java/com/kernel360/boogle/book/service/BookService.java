package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.model.BookRequest;
import com.kernel360.boogle.book.model.BookViewRequest;

import java.util.*;


public interface BookService {

    public List<BookEntity> findAllBook();

    public void saveBook(BookDTO book);

    public void updateBook(BookDTO book);

    List<BookEntity> findBookBySearchWord(String searchWord);

    List<BookDTO> findAladinBook(BookRequest request);

    Optional<BookEntity> findById(Long bookId);

    // Optional<List<BookEntity>> findAllByDeletedYnOrderByBookIdDesc(String deletedYn);

    public void deleteBook(BookViewRequest bookViewRequest);
}
