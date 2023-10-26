package com.kernel360.boogle.book.controller;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/mainPage")
    public ModelAndView viewPostForm() {
        ModelAndView mv = new ModelAndView("mainPage");
        List<BookEntity> bookList = new ArrayList<>(bookService.findAllBook());
        mv.addObject("books", bookList);
        return mv;
    }

    @GetMapping("/admin/login")
    String login(){
        return "login";
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
}