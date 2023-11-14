package com.kernel360.boogle.bookreport.service;

import com.kernel360.boogle.book.db.BookRepository;
import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.db.BookReportRepository;
import com.kernel360.boogle.bookreport.model.BookReportDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class BookReportServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookReportRepository bookReportRepository;

    @Autowired
    private BookReportService bookReportService;

    @Test
    void createBookReport() {
        long rowCount = bookReportRepository.count();
        BookReportDTO bookReportDTO = BookReportDTO.builder()
                .bookReportEntity(
                        BookReportEntity.builder()
                                .bookEntity(bookRepository.findById(54L).get())
                                .isPublic("N")
                                .title("타이틀1")
                                .content("독후감1")
                                .build()
                )
                .build();
        bookReportService.createBookReport(bookReportDTO);
        assertEquals(bookReportRepository.count(), rowCount + 1);
    }

    @Test
    void getBookReportById() {
        Optional<BookReportEntity> result = bookReportService.getBookReportById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Y", result.get().getIsPublic());
        assertEquals("독후감1", result.get().getContent());
    }

    @Test
    void getPublicBookReports() {
        Page<BookReportEntity> result = bookReportService.getPublicBookReports("Y", 0);
        assertEquals(result.getContent().size(), 6);
    }

    @Test
    void getMyBookReports() {
        
        assertEquals(
                bookReportService.getMyBookReports(
                        1L,
                        0
                ).getContent().size(),
                6
        );
    }

    @Test
    void getAllBookReports() {
        assertEquals(
                bookReportService.getAllBookReports(
                        1
                ).getContent().size(),
                6
        );
    }


    @Test
    void updateBookReport() {
        BookReportDTO bookReportDTO = BookReportDTO.builder()
                .bookReportEntity(
                    BookReportEntity.builder()
                        .id(1L)
                        .bookEntity(bookRepository.findById(54L).get())
                        .isPublic("N")
                        .title("타이틀1")
                        .content("독후감1")
                        .build()
                )
                .build();
        bookReportService.updateBookReport(bookReportDTO);
        assertThat(bookReportRepository.findById(1L).get().getIsPublic()).isEqualTo("N");
    }

    @Test
    void deleteBookReport() {
        Long bookReportId = 1L;
        bookReportService.deleteBookReport(bookReportId);
        assertThat(bookReportRepository.findById(bookReportId).get().getIsDeleted()).isEqualTo("Y");
    }
}