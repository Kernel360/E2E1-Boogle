package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.db.BookRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<BookEntity> findBookBySearchWord(String searchWord) {
        Set<BookEntity> uniqueBooks = new HashSet<>();

        uniqueBooks.addAll(bookRepository.findBookEntitiesByBookTitleContaining(searchWord));
        uniqueBooks.addAll(bookRepository.findBookEntitiesByAuthorContaining(searchWord));
        uniqueBooks.addAll(bookRepository.findBookEntitiesByPublisherContaining(searchWord));

        return new ArrayList<>(uniqueBooks);
    }
}
