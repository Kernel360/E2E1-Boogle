package com.kernel360.boogle.bookreport.controller;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.model.BookReportDTO;
import com.kernel360.boogle.bookreport.service.BookReportService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Api(tags = {"독후감 관련 API"})
@RestController
public class BookReportController {
    private final BookReportService bookReportService;

    public BookReportController(BookReportService bookReportService) {
        this.bookReportService = bookReportService;
    }

    @PostMapping("/book-report")
    public void createBookReport(@RequestBody BookReportDTO bookReport) {
        bookReportService.createBookReport(bookReport);
    }

    @GetMapping("/book-report")
    public ModelAndView getBookReportById(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("bookreport/book-detail");
        BookReportEntity bookReport = bookReportService.getBookReportById(id).get();
        mv.addObject("bookReport", bookReport);
        return mv;
    }

    @GetMapping("/my-book-reports")
    public ModelAndView getMyBookReports(
            @RequestParam Long memberId,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        ModelAndView mv = new ModelAndView("bookreport/my-book-reports");
        Page<BookReportEntity> bookReports = bookReportService.getMyBookReports(memberId, page);
        mv.addObject("bookReports", bookReports);
        return mv;
    }

    @GetMapping("/book-reports")
    public ModelAndView getPublicBookReports(
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        ModelAndView mv = new ModelAndView("bookreport/public-book-reports");
        Page<BookReportEntity> bookReports = bookReportService.getPublicBookReports("Y", page);
        mv.addObject("bookReports", bookReports);
        return mv;
    }

    @PatchMapping("/book-report")
    public void updateBookReport(@RequestBody BookReportDTO bookReport) {
        bookReportService.updateBookRepoert(bookReport);
    }

    @PatchMapping("/book-report/delete")
    public String deleteBookReport(@RequestParam BookReportDTO bookReport) {
        bookReportService.deleteBookReport(bookReport.getId());
        return "redirect:/my-book-reports";
    }
}
