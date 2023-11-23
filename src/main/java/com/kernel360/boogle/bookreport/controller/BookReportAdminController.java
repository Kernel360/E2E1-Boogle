package com.kernel360.boogle.bookreport.controller;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.model.BookReportDTO;
import com.kernel360.boogle.bookreport.service.BookReportService;
import com.kernel360.boogle.global.batch.MailForNewRelease;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Api(tags = {"독후감 관련 Admin API"})
@RestController
@Slf4j
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
        return mv;
    }

    @GetMapping("/admin/book-report")
    public ModelAndView getBookReportById(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("bookreport/admin/book-report-detail");
        BookReportEntity bookReport = bookReportService.getBookReportById(id).get();
        mv.addObject("bookReport", bookReport);
        return mv;
    }

    @DeleteMapping("admin/book-report")
    public void deleteBookReport(@RequestBody BookReportDTO bookReport) {
        log.info("ADMIN에 의해 독후감 삭제가 수행됨. 삭제된 독후감 정보: " + bookReportService.getBookReportById(bookReport.getId()));
        bookReportService.deleteBookReport(bookReport.getId());
    }

}
