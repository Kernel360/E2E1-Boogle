package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookDTO;

import java.util.List;

public interface BookService {
    public List<BookEntity> findAllBook();

    public void saveBook(BookDTO book);

    public void updateBook(BookDTO book);

}
