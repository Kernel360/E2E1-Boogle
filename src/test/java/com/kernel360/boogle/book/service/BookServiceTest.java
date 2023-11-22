package com.kernel360.boogle.book.service;

import com.kernel360.boogle.book.db.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void getBookById() {
        // given
        Long validId = 4L;
        Long inValidId = 99999999L;
        // when
        Optional<BookEntity> book = bookService.getBookById(validId);
        Optional<BookEntity> empty = bookService.getBookById(inValidId);
        // then
        then(book.isPresent()).isTrue();
        then(empty.isEmpty()).isTrue();
    }

    @Test
    void getBooks() {
        // given
        int page = 0;
        // when
        Page<BookEntity> bookPage = bookService.getBooks(page);
        BookEntity book = bookPage.getContent().get(0);
        // then
        then(book.getTitle()).isEqualTo("TEST_TITLE");
        then(book.getCategoryId()).isEqualTo(1000L);
    }

    @Test
    void getBooksByAuthor() {
        // given
        int page = 0;
        // when
        Page<BookEntity> bookPage = bookService.getBooksByAuthor(page, "박은종");
        BookEntity book = bookPage.getContent().get(0);
        // then
        then(book.getPublisher()).isEqualTo("이지스퍼블리싱");
    }

    @Test
    void getBooksByTitle() {
        // given
        int page = 0;
        // when
        Page<BookEntity> bookPage = bookService.getBooksByTitle(page, "Do it! 자바");
        BookEntity book = bookPage.getContent().get(0);
        // then
        then(book.getPublisher()).isEqualTo("이지스퍼블리싱");
    }

    @Test
    void getBooksByPublisher() {
        // given
        int page = 0;
        // when
        Page<BookEntity> bookPage = bookService.getBooksByPublisher(page, "이지스퍼블리싱");
        BookEntity book = bookPage.getContent().get(0);
        // then
        then(book.getId()).isEqualTo(195);
    }
}