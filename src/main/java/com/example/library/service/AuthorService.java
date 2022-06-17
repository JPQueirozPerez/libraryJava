package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Iterable<Author> getAllAuthors() {

        Iterable<Author> authors = authorRepository.findAll();

        return authors;
    }

    public Author createAuthor(Author author){

        Author authorCreated = authorRepository.save(author);

        return authorCreated;
    }

    public Optional<Author> findAuthorById(Long authorId){

        return authorRepository.findById(authorId);
    }


}