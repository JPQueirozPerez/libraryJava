package com.example.library.repository;

import com.example.library.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

// Optional<Author> findAuthorById(long authorId);
}