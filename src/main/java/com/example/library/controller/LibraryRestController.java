package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:8080", methods={RequestMethod.GET,RequestMethod.POST})
public class LibraryRestController {

    @Autowired
    BookService bookService;


    @GetMapping("getBooks")
    public Iterable<Book> getBooks() {
        //
        return bookService.getAllBooks();

    }
}