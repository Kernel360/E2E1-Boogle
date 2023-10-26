package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.db.BookRepository;
import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.model.BookRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<BookEntity> findAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(BookDTO book) {
        bookRepository.save(book.getBookEntity());
    }

    @Override
    public void updateBook(BookDTO book) {
        book.getBookEntity().setLastModifiedBy("TEST"); // 로그인 사용자 정보 들어가야함
        bookRepository.save(book.getBookEntity());
    }

    @Override
    public List<BookEntity> findBookBySearchWord(String searchWord) {
        Set<BookEntity> uniqueBooks = new HashSet<>();

        uniqueBooks.addAll(bookRepository.findBookEntitiesByBookTitleContaining(searchWord));
        uniqueBooks.addAll(bookRepository.findBookEntitiesByAuthorContaining(searchWord));
        uniqueBooks.addAll(bookRepository.findBookEntitiesByPublisherContaining(searchWord));

        return new ArrayList<>(uniqueBooks);
    }

    @Override
    public List<BookDTO> findAladinBook(BookRequest request) {

        return null;
    }

    @Override
    public Optional<BookEntity> findById(Long bookId) {
        return bookRepository.findById(bookId);
    }
}
