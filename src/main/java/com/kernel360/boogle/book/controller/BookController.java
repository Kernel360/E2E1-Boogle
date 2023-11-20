package com.kernel360.boogle.book.controller;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookSearchType;
import com.kernel360.boogle.book.service.BookService;
import com.kernel360.boogle.bookreport.db.BookReportEntity;
import com.kernel360.boogle.bookreport.service.BookReportService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Api(tags = "도서 관련 User API")
@RestController
public class BookController {

    private final BookService bookService;
    private final BookReportService bookReportService;

    public BookController(BookService bookService, BookReportService bookReportService) {
        this.bookService = bookService;
        this.bookReportService = bookReportService;
    }

    @GetMapping("/books")
    public ModelAndView getBooks(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord,
            @RequestParam(value = "searchType", required = false, defaultValue = "") String searchType) {
        ModelAndView mv = new ModelAndView("book/books");
        Page<BookEntity> books = null;

        if (searchType.equals(BookSearchType.TITLE.getType())) {
            books = bookService.getBooksByTitle(page, searchWord);
        } else if (searchType.equals(BookSearchType.AUTHOR.getType())) {
            books = bookService.getBooksByAuthor(page, searchWord);
        } else if (searchType.equals(BookSearchType.PUBLISHER.getType())) {
            books = bookService.getBooksByPublisher(page, searchWord);
        } else {
            books = bookService.getBooks(page, searchWord);
        }

        mv.addObject("books", books);
        return mv;
    }

    @GetMapping("book")
    public ModelAndView getBookById(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("book/book-detail");
        BookEntity book = bookService.getBookById(id).get();
        mv.addObject("book", book);
        List<BookReportEntity> bookReports = bookReportService.getPublicBookReportsByBookId(id);
        mv.addObject("bookReports", bookReports);
        return mv;
    }
}
