package ru.fomin.sql2o.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fomin.sql2o.services.IBookService;

@Controller
@RequestMapping("/book")
public class BookController {

    IBookService bookService;

    @Autowired
    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String getBookList(){

    }

}
