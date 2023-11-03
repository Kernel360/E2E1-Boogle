package com.kernel360.boogle.bookreport.controller;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.model.BookReportDTO;
import com.kernel360.boogle.bookreport.service.BookReportService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Api(tags = {"독후감 관련 Admin API"})
@RestController
public class BookReportAdminController {

    private final BookReportService bookReportService;

    public BookReportAdminController(BookReportService bookReportService) {
        this.bookReportService = bookReportService;
    }

    @GetMapping("/admin/book-reports")
    public ModelAndView getBookReports(@RequestParam(value = "page", defaultValue = "0") int page) {
        ModelAndView mv = new ModelAndView("bookreport/admin/book-reports");
        Page<BookReportEntity> bookReports = bookReportService.getAllBookReports(page);
        mv.addObject("bookReports", bookReports);

        int currentPageNumber = bookReports.getNumber();
        int totalPagesNumber = bookReports.getTotalPages();
        mv.addObject("currentPageNumber", currentPageNumber);
        mv.addObject("totalPagesNumber", totalPagesNumber);
        return mv;
    }

    @GetMapping("/admin/book-report")
    public ModelAndView getBookReportById(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("bookreport/admin/book-report-detail");
        BookReportEntity bookReport = bookReportService.getBookReportById(id).get();
        mv.addObject("bookReport", bookReport);
        return mv;
    }

    @PatchMapping("admin/book-report/delete")
    public void deleteBookReport(@RequestBody BookReportDTO bookReport) {
        bookReportService.deleteBookReport(bookReport.getId());
    }

}
