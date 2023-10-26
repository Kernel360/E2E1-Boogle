package com.kernel360.boogle.book.controller;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
}