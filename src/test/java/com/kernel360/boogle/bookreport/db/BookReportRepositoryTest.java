package com.kernel360.boogle.bookreport.db;

import com.kernel360.boogle.book.db.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class BookReportRepositoryTest {

    @Autowired
    BookReportRepository bookReportRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    void save() {
        BookReportEntity bookReport = BookReportEntity.builder()
                .bookEntity(bookRepository.findById(4L).get())
                .memberId(1L)
                .isPublic("Y")
                .title("test report")
                .content("test content")
                .createdBy("1")
                .build();

        BookReportEntity result = bookReportRepository.save(bookReport);

        then(result.getId()).isNotNull();
        then(result.getTitle()).isEqualTo("test report");
        then(result.getContent()).isEqualTo("test content");
        then(result.getBookEntity().getId()).isEqualTo(4L);
    }

    @Test
    void findAllByIsPublicEqualsAndIsDeletedNotOrderByIdDesc() {
        then(bookReportRepository.findAllByIsPublicEqualsAndIsDeletedNotOrderByIdDesc(
                "Y",
                "Y",
                PageRequest.of(0, 6)
        ).getContent().size()).isEqualTo(6);
    }


    @Test
    void findAllByMemberIdEqualsAndIsDeletedNotOrderByIdDesc() {
        then(bookReportRepository.findAllByMemberIdEqualsAndIsDeletedNotOrderByIdDesc(
                1L,
                "Y",
                PageRequest.of(0, 6)
        ).getContent().size()).isEqualTo(6);
    }

    @Test
    void findAllByIsDeletedNotOrderByIdDesc() {
        then(bookReportRepository.findAllByIsDeletedNotOrderByIdDesc(
                "Y",
                PageRequest.of(0, 6)
        ).getContent().size()).isEqualTo(6);
    }

    @Test
    void findById() {
        BookReportEntity bookReportEntity = bookReportRepository.findById(6L).get();

        then(bookReportEntity.getId()).isNotNull();
        then(bookReportEntity.getTitle()).isEqualTo("타이틀6");
        then(bookReportEntity.getContent()).isEqualTo("독후감6");
        then(bookReportEntity.getBookEntity().getId()).isEqualTo(54L);
    }
}