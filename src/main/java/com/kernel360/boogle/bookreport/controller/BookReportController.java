package com.kernel360.boogle.bookreport.controller;

import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.model.BookReportDTO;
import com.kernel360.boogle.bookreport.service.BookReportService;
import com.kernel360.boogle.reply.model.ReplyDTO;
import com.kernel360.boogle.reply.model.ReplyResponse;
import com.kernel360.boogle.reply.service.ReplyService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Api(tags = {"독후감 관련 API"})
@RestController
public class BookReportController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final BookReportService bookReportService;
    private final ReplyService replyService;

    public BookReportController(BookReportService bookReportService, ReplyService replyService) {
        this.bookReportService = bookReportService;
        this.replyService = replyService;
    }


    @GetMapping("/book-report/create")
    public ModelAndView createBookReport() {
        ModelAndView mv = new ModelAndView("bookreport/book-report-create");
        return mv;
    }

    @PostMapping("/book-report")
    public void createBookReport(@RequestBody BookReportDTO bookReport) {
        bookReportService.createBookReport(bookReport);
    }

    @GetMapping("/book-report")
    public ModelAndView getBookReportById(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("bookreport/book-report-detail");
        BookReportEntity bookReport = bookReportService.getBookReportById(id).get();
        mv.addObject("bookReport", bookReport);

        List<ReplyDTO> replyDTOS = replyService.getRepliesByBookReportId(id);
        var replies = ReplyResponse.organizeChildReplies(replyDTOS);
        mv.addObject("replies", replies);
        return mv;
    }

    @GetMapping("/my-book-reports")
    public ModelAndView getMyBookReports(
            @RequestParam(value = "memberId") Long memberId,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        ModelAndView mv = new ModelAndView("bookreport/book-reports");
        Page<BookReportEntity> bookReports = bookReportService.getMyBookReports(memberId, page);
        mv.addObject("bookReports", bookReports);
        int currentPageNumber = bookReports.getNumber();
        int totalPagesNumber = bookReports.getTotalPages();
        mv.addObject("currentPageNumber", currentPageNumber);
        mv.addObject("totalPagesNumber", totalPagesNumber);
        return mv;
    }

    @GetMapping("/book-reports")
    public ModelAndView getPublicBookReports(
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        ModelAndView mv = new ModelAndView("bookreport/book-reports");
        Page<BookReportEntity> bookReports = bookReportService.getPublicBookReports("Y", page);
        mv.addObject("bookReports", bookReports);
        int currentPageNumber = bookReports.getNumber();
        int totalPagesNumber = bookReports.getTotalPages();
        mv.addObject("currentPageNumber", currentPageNumber);
        mv.addObject("totalPagesNumber", totalPagesNumber);
        return mv;
    }

    @GetMapping("/book-report/update")
    public ModelAndView updateBookReport(@RequestParam(value = "bookReportId") Long bookReportId) {
        ModelAndView mv = new ModelAndView("bookreport/book-report-update");
        BookReportEntity bookReport = bookReportService.getBookReportById(bookReportId).get();
        mv.addObject("bookReport", bookReport);
        return mv;
    }

    @PatchMapping("/book-report")
    public void updateBookReport(@RequestBody BookReportDTO bookReport) {
        bookReportService.updateBookReport(bookReport);
    }

    @PatchMapping("/book-report/delete")
    public void deleteBookReport(@RequestBody BookReportDTO bookReport) {
        bookReportService.deleteBookReport(bookReport.getId());
    }
}
