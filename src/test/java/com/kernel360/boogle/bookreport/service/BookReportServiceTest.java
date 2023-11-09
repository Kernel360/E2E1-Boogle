package com.kernel360.boogle.bookreport.service;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class BookReportServiceTest {

    @Autowired
    private BookReportService bookReportService;

    @Test
    void createBookReport() {
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
}