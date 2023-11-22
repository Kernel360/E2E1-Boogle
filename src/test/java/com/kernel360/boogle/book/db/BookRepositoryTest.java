package com.kernel360.boogle.book.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Test
    void save() {
        // given
        BookEntity bookEntity = BookEntity.builder()
                .title("TEST_TITLE")
                .categoryId(1000L)
                .author("TEST_AUTHOR")
                .publisher("TEST_PUBLISHER")
                .isbn(100000000000L)
                .publishDate(LocalDate.now().minusMonths(1L))
                .createdBy("USER_ADMIN")
                .createdAt(LocalDateTime.now())
                .build();
        // when
        BookEntity book = bookRepository.save(bookEntity);
        // then
        assertThat(book.getTitle()).isEqualTo("TEST_TITLE");
    }
    @Test
    void findAll() {
        // given
        List<BookEntity> Books = bookRepository.findAll();
        // when
        BookEntity book = Books.get(0);
        // then
        then(book.getTitle()).isEqualTo("모던 리액트 Deep Dive - 리액트의 핵심 개념과 동작 원리부터 Next.js까지, 리액트의 모든 것");
        then(book.getCategoryId()).isEqualTo(6627);
    }
    @Test
    void findAllPage() {
        // given
        int pageSize = 10;
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<BookEntity> bookPage = bookRepository.findAll(pageable);
        // when
        BookEntity lastBookOfThePage = bookPage.getContent().get(pageSize - 1);
        // then
        then(lastBookOfThePage.getIsbn()).isEqualTo(9788965403722L);
    }
    @Test
    void findBookEntitiesByTitleContainingOrderByIdDesc() {
        // given
        int pageSize = 10;
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<BookEntity> bookPage = bookRepository.findBookEntitiesByTitleContainingOrderByIdDesc("TEST_TITLE", pageable);
        // when
        BookEntity firstSearchedBook = bookPage.getContent().get(0);
        // then
        then(firstSearchedBook.getIsbn()).isEqualTo(100000000000L);
    }
    @Test
    void findBookEntitiesByAuthorContainingOrderByIdDesc() {
        // given
        int pageSize = 10;
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<BookEntity> bookPage = bookRepository.findBookEntitiesByAuthorContainingOrderByIdDesc("TEST_AUTHOR", pageable);
        // when
        BookEntity firstSearchedBook = bookPage.getContent().get(0);
        // then
        then(firstSearchedBook.getIsbn()).isEqualTo(100000000000L);
    }
    @Test
    void findBookEntitiesByPublisherContainingOrderByIdDesc() {
        // given
        int pageSize = 10;
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<BookEntity> bookPage = bookRepository.findBookEntitiesByPublisherContainingOrderByIdDesc("TEST_PUBLISHER", pageable);
        // when
        BookEntity firstSearchedBook = bookPage.getContent().get(0);
        // then
        then(firstSearchedBook.getIsbn()).isEqualTo(100000000000L);
    }
    @Test
    void findById() {
        // given
        Long validId = 4L;
        Long inValidId = 99999999L;
        // when
        Optional<BookEntity> book = bookRepository.findById(validId);
        Optional<BookEntity> empty = bookRepository.findById(inValidId);
        // then
        then(book.isPresent()).isTrue();
        then(empty.isEmpty()).isTrue();
    }
    @Test
    void findAllByOrderByIdDesc() {
        // given
        int pageSize = 10;
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<BookEntity> Books = bookRepository.findAllByOrderByIdDesc(pageable);
        // when
        BookEntity book = Books.getContent().get(0);
        // then
        then(book.getTitle()).isEqualTo("TEST_TITLE");
        then(book.getCategoryId()).isEqualTo(1000L);
    }
    @Test
    void deleteById() {
        // given
        BookEntity bookEntity = BookEntity.builder()
                .title("TEST_TITLE")
                .categoryId(1000L)
                .author("TEST_AUTHOR")
                .publisher("TEST_PUBLISHER")
                .isbn(100000000000L)
                .publishDate(LocalDate.now().minusMonths(1L))
                .createdBy("USER_ADMIN")
                .createdAt(LocalDateTime.now())
                .build();
        BookEntity book = bookRepository.save(bookEntity);
        // when
        Long deletedId = book.getId();
        bookRepository.deleteById(deletedId);
        // then
        then(bookRepository.findById(deletedId).isEmpty()).isTrue();
    }
}
