package com.kernel360.boogle.controller.admin.book;

import com.kernel360.boogle.dto.BookDTO;
import com.kernel360.boogle.entity.BookEntity;
import com.kernel360.boogle.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final BookService bookService;

    public AdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/login")
    String login(){
        return "login";
    }

    @PostMapping ("/book")
    public void saveBook(@RequestBody BookDTO book) {
        bookService.saveBook(book);
    }

    @GetMapping("/book")
    public List<BookEntity> getBook() {
        return bookService.findAllBook();
    }
}
