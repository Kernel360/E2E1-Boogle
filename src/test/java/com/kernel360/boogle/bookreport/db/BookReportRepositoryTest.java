package com.kernel360.boogle.bookreport.db;

import com.kernel360.boogle.book.db.BookRepository;
import com.kernel360.boogle.member.db.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@Transactional
class BookReportRepositoryTest {

    @Autowired
    BookReportRepository bookReportRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        BookReportEntity bookReport = BookReportEntity.builder()
                .bookEntity(bookRepository.findById(4L).get())
                .memberEntity(memberRepository.findById(1L).get())
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
    void findAllByIsPublicOrderByIdDesc() {
        then(bookReportRepository.findAllByIsPublicOrderByIdDesc(
                "Y",
                PageRequest.of(0, 6)
        ).getContent().size()).isEqualTo(6);
    }


    @Test
    void findAllByMemberIdOrderByIdDesc() {
        then(bookReportRepository.findAllByMemberEntity_IdOrderByIdDesc(
                1L,
                PageRequest.of(0, 6)
        ).getContent().size()).isEqualTo(6);
    }

    @Test
    void findAllByOrderByIdDesc() {
        then(bookReportRepository.findAllByOrderByIdDesc(PageRequest.of(0, 6))
                .getContent().size()).isEqualTo(6);
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