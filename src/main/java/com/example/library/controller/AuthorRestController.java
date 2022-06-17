package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api")
public class AuthorRestController {

    @Autowired
    static
    AuthorService authorService;

    //CRUD: read
    @GetMapping("authors")
    public static Iterable<Author> getAllAuthors() {
        //
        return authorService.getAllAuthors();
    }

    //CRUD: read, find one author by id
    @GetMapping("getAuthor")
    public Author findAuthorById(Long authorId){
        //
        Optional<Author> authorFound = authorService.findAuthorById(authorId);
        if (authorFound.isPresent()) return authorFound.get();

        return null;
    }

    //CRUD: create
    @PostMapping(path="addAuthor", consumes = "application/JSON")
    public Author addAuthor(@RequestBody Author author){
        //
        Author authorCreated = authorService.createAuthor(author);
        return authorCreated ;
    }
}