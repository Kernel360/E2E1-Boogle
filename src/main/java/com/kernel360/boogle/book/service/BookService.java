package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface BookService {
    public List<BookEntity> findAllBook();

    public void saveBook(BookDTO book);

    public void updateBook(BookDTO book);

    List<BookEntity> findBookBySearchWord(String searchWord);
}
