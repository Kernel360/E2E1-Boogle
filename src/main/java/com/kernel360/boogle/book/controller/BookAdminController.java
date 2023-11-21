package com.kernel360.boogle.book.controller;

import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.model.BookDTO;
import com.kernel360.boogle.book.model.BookSearchType;
import com.kernel360.boogle.book.model.BookViewRequest;
import com.kernel360.boogle.book.service.BookService;
import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.model.MemberDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Api(tags = {"도서 관련 Admin API"})
@RestController
@Slf4j
public class BookAdminController {

    private final BookService bookService;

    public BookAdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/admin/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin/book/create")
    public ModelAndView createBook() {
        ModelAndView mv = new ModelAndView("book/admin/book-create");
        return mv;
    }

    @PostMapping("/admin/book")
    public void createBook(@RequestBody BookDTO book, @AuthenticationPrincipal MemberEntity memberEntity) {
        bookService.createBook(book, MemberDTO.from(memberEntity));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/admin/books")
    public ModelAndView getBooks(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord,
            @RequestParam(value = "searchType", required = false, defaultValue = "") String searchType) {
        ModelAndView mv = new ModelAndView("book/admin/books");
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

    @GetMapping("/admin/book")
    public ModelAndView getBookById(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("book/admin/book-update");
        BookEntity book = bookService.getBookById(id).get();
        mv.addObject("book", book);
        return mv;
    }

    @PatchMapping("/admin/book")
    public void updateBook(@RequestBody BookDTO book, @AuthenticationPrincipal MemberEntity memberEntity) {
        bookService.updateBook(book, MemberDTO.from(memberEntity));
    }

    @DeleteMapping("/admin/book")
    public String deleteBook(@RequestBody BookViewRequest bookViewRequest) {
        log.info("도서 삭제가 수행됨. 삭제된 도서 정보: " + bookService.getBookById(bookViewRequest.getId()));
        bookService.deleteBook(bookViewRequest);
        return "redirect:/admin/books";
    }
}