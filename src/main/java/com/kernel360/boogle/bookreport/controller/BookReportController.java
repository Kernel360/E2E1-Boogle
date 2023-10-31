package com.kernel360.boogle.bookreport.controller;

import com.kernel360.boogle.bookreport.model.BookReportDTO;
import com.kernel360.boogle.bookreport.service.BookReportService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookReportController {
    private final BookReportService bookReportService;

    public BookReportController(BookReportService bookReportService) {
        this.bookReportService = bookReportService;
    }

    @PostMapping("/book-report")
    public void createBookReport() {

    }

    @GetMapping("/book-report")
    public List<BookReportDTO> getBookReportById() {
        return new ArrayList<>();
    }

    @GetMapping("/my-book-reports")
    public List<BookReportDTO> getMyBookReports() {
        return new ArrayList<>();
    }

    @GetMapping("/book-reports")
    public List<BookReportDTO> getPublicBookReports() {
        return new ArrayList<>();
    }

    @PatchMapping("/book-report")
    public void updateBookReport() {

    }

    @PatchMapping("/book-report/delete")
    public void deleteBookReport() {

    }
}
