package com.kernel360.boogle.aladin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AladinBookController {

    private final AladinBookService aladinBookService;

    public AladinBookController(AladinBookService aladinBookService) {
        this.aladinBookService = aladinBookService;
    }

    @GetMapping("/aladin/api-test")
    public void getAladinBook(AladinBookRequest request) { //List<BookDTO>
        aladinBookService.getAladinBook(request);
    }
}
