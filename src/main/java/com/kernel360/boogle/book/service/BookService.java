package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.db.BookRepository;
import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.model.BookViewRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private static final Integer PAGE_SIZE = 6;

    public void createBook(BookDTO book) {
        book.getBookEntity().setCreatedBy("TEST"); // 로그인 사용자 정보 들어가야 함
        bookRepository.save(book.getBookEntity());
    }

    public Optional<BookEntity> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public Page<BookEntity> getBooks(int page, String searchWord) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookRepository.findAllByIsDeletedNotOrderByIdDesc(pageable, "Y");
    }

    public Page<BookEntity> getBooksByAuthor(int page, String searchWord) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookRepository.findBookEntitiesByAuthorContainingAndIsDeletedNotOrderByIdDesc(searchWord, pageable, "Y");
    }

    public Page<BookEntity> getBooksByTitle(int page, String searchWord) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookRepository.findBookEntitiesByTitleContainingAndIsDeletedNotOrderByIdDesc(searchWord, pageable, "Y");

    }

    public Page<BookEntity> getBooksByPublisher(int page, String searchWord) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return bookRepository.findBookEntitiesByPublisherContainingAndIsDeletedNotOrderByIdDesc(searchWord, pageable, "Y");
    }

    public void updateBook(BookDTO book) {
        book.getBookEntity().setLastModifiedBy("TEST"); // 로그인 사용자 정보 들어가야함
        bookRepository.save(book.getBookEntity());
    }

    public void deleteBook(BookViewRequest bookViewRequest) {
        bookRepository.findById(bookViewRequest.getId())
                .map(
                        it -> {
                            it.setIsDeleted("Y");
                            it.setDeletedAt(LocalDateTime.now());
                            bookRepository.save(it);
                            return it;
                        }
                );
    }
}
