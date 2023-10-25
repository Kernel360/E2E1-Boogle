package com.kernel360.boogle.service;

import com.kernel360.boogle.entity.Book;
import com.kernel360.boogle.repository.book.BookRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }
}
