package com.kernel360.boogle.controller.user.book;

import com.kernel360.boogle.entity.BookEntity;
import com.kernel360.boogle.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/")
public class BookController {

    @Autowired
    private BookService bookService;
    @GetMapping("/mainPage")
    public ModelAndView viewPostForm() {
        ModelAndView mv = new ModelAndView("mainPage");
        List<BookEntity> bookList = new ArrayList<>(bookService.findAllBook());
        mv.addObject("books", bookList);
        return mv;
    }
}