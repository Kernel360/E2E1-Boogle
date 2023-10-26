package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.db.BookRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookEntity> findAllBook() {
        return bookRepository.findAll();
    }

    public void saveBook(BookDTO book) {
        bookRepository.save(book.getBookEntity());
    }

}
