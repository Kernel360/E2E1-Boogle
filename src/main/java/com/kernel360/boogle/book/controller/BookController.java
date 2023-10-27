package com.kernel360.boogle.book.controller;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.model.BookViewRequest;
import com.kernel360.boogle.book.service.BookService;
import com.kernel360.boogle.book.service.BookServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final BookService bookService;
    private final BookServiceImpl bookServiceImpl;

    public BookController(BookService bookService, BookServiceImpl bookServiceImpl) {
        this.bookService = bookService;
        this.bookServiceImpl = bookServiceImpl;
    }

    @GetMapping("/api/mainPage")
    public ModelAndView viewPostForm() {
        ModelAndView mv = new ModelAndView("mainPage");
        List<BookEntity> bookList = bookServiceImpl.findMainPage();
        mv.addObject("books", bookList);
        return mv;
    }

    @PostMapping(value="/api/mainPage")
    public String deleteBook(
            @RequestBody BookViewRequest bookViewRequest
            ) {
        bookServiceImpl.deleteBook(bookViewRequest);
        return "redirect:/api/mainPage";
    }

    @GetMapping("/admin/login")
    String login(){
        return "login";
    }

    @GetMapping("/admin")
    String adminPage(){
        return "amendBook";
    }

    @PostMapping("/admin/book")
    public void saveBook(@RequestBody BookDTO book) {
        bookService.saveBook(book);
    }

    @GetMapping("/admin/book")
    public List<BookEntity> getBook() {
        return bookService.findAllBook();
    }

    @PatchMapping("/admin/book")
    public void updateBook(@RequestBody BookDTO book) {
        bookService.updateBook(book);
    }  

    // 검색어(제목,저자,출판사)에 따른 도서목록 검색
    @GetMapping("/api/bookSearch")
    public ModelAndView getBookByBookTitle(@RequestParam String searchWord) {
        ModelAndView mv = new ModelAndView("bookSearch");
        List<BookEntity> bookList = new ArrayList<>(bookService.findBookBySearchWord(searchWord));
        mv.addObject("books", bookList);
        return mv;
    }

    @GetMapping("/admin/book-detail")
    public ModelAndView getBookDetail(@RequestParam Long bookId) {
        ModelAndView mv = new ModelAndView("amendBook");
        BookEntity book = bookService.findById(bookId).get();
        mv.addObject("book", book);
        return mv;
    }

}