package com.kernel360.boogle.book.controller;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.model.BookSearchType;
import com.kernel360.boogle.book.model.BookViewRequest;
import com.kernel360.boogle.book.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@PreAuthorize("hasRole('ADMIN')")
@Api(tags = {"도서 관련 API"})
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;

    }

    @GetMapping("/admin/books")
    public ModelAndView pageList(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord,
            @RequestParam(value = "searchType", required = false, defaultValue = "") String searchType) {
        ModelAndView mv = new ModelAndView("book/admin/book-list");
        Page<BookEntity> books = null;

        if(searchType.equals(BookSearchType.TITLE.getType())) {
            books = bookService.getBooksByTitle(page, searchWord);
        } else if (searchType.equals(BookSearchType.AUTHOR.getType())) {
            books = bookService.getBooksByAuthor(page, searchWord);
        } else if (searchType.equals(BookSearchType.PUBLISHER.getType())) {
            books = bookService.getBooksByPublisher(page, searchWord);
        } else {
            books = bookService.getBooks(page, searchWord);
        }

        mv.addObject( "books",books);
        return mv;
    }

    @PatchMapping("/admin/book/delete")
    public String deleteBook(
            @RequestBody BookViewRequest bookViewRequest
    ) {
        bookService.deleteBook(bookViewRequest);
        return "redirect:/admin/books";
    }

    @PostMapping("/admin/book")
    public void createBook(@RequestBody BookDTO book) {
        bookService.createBook(book);
    }

    @PatchMapping("/admin/book")
    public void updateBook(@RequestBody BookDTO book) {
        bookService.updateBook(book);
    }

    @GetMapping("/admin/book")
    public ModelAndView getBookDetail(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("book/admin/book-update");
        BookEntity book = bookService.getBookById(id).get();
        mv.addObject("book", book);
        return mv;
    }
    @GetMapping("/admin/book/create")
    public ModelAndView createBook() {

        return new ModelAndView("book/admin/book-create");
    }
}