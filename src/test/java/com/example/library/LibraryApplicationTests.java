package com.example.library;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LibraryApplicationTests {
    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorService authorService;
    @Autowired
    AuthorRepository authorRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void createBooks() {

        Faker faker = new Faker();

        for (int i = 0; i < 150; i++) {
            String title = faker.book().title();
            int pages = faker.random().nextInt(100, 1000);
            int year = faker.random().nextInt(1800, 2020);

            Book book = new Book(title, pages, year, "56756-DFG2T-55" + i);
            bookService.createBook(book);
        }

        assertEquals(bookRepository.count(), 150);

    }

    @Test
    void deleteBookById() {
        long l;
        for (int i = 900; i < 1500; i++) {
            l = i;
            Optional<Book> book = bookService.findBookById(l);
            if (book.isPresent()) {
                bookService.deleteBookById(l);
            }
        }
    }

    @Test
    void createAuthors(){

        Faker faker = new Faker();

//        LocalDate date1 = LocalDate.of (1856,1,10);
//        Author author1 = new Author("Leon", "Tolstoy", date1 );
//        LocalDate date2 = LocalDate.of (1882,1,12);
//        Author author2 = new Author("Virginia", "Wolf", date2 );
//        LocalDate date3 = LocalDate.of (1782,1,5);
//        Author author3 = new Author("Emile", "Zona", date2 );
//        //to repo-DB via authorService
//        authorService.createAuthor(author1);
//        authorService.createAuthor(author2);
//        authorService.createAuthor(author3);

        for (int i = 0; i < 100; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            int month = faker.random().nextInt(1, 12);
            int year = faker.random().nextInt(1750, 2000);
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localDate = LocalDate.of (year,month,10);
            Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            Author author = new Author(firstName, lastName, date );

            authorService.createAuthor(author);
        }

        assertEquals(authorRepository.count(), 100);

    }

    @Test
    void assignAuthorToNewBooks(){
        Faker faker = new Faker();
        //find by id just one author
        Optional<Author> authorFound = authorRepository.findById(480L);
        //if author exists, then..
        if(authorFound.isPresent()) {
//            //create books
//            Book book1 = new Book("War and Peace",  462, 1867, "SSSGV-5-ERG5-6567");
//            Book book2 = new Book("Chilhood", 235, 1852, "56756-DFG2T-554");
//            //assign author to books
//            book1.setAuthorId(authorFound.get());
//            book2.setAuthorId(authorFound.get());
//            //save books with author
//            bookService.createBook(book1);
//            bookService.createBook(book2);
            for (int i = 0; i < 150; i++) {
                String title = faker.book().title();
                int pages = faker.random().nextInt(100, 1000);
                int year = faker.random().nextInt(1800, 2020);
                Book book = new Book(title, pages, year, "56756-DFG2T-55" + i);
//                book.setAuthor(authorFound.get());
                bookService.createBook(book);
            }
        }

    }

    @Test
    void assignNewBooksToAuthor(){
        //find by id just one author
        Optional<Author> authorFound = authorRepository.findById(5L);
        //if author exists, then..
        if(authorFound.isPresent()) {
            //create books
            Book book1 = new Book("War and Peace",  462, 1867, "SSSGV-5-ERG5-6567");
            Book book2 = new Book("Chilhood", 235, 1852, "56756-DFG2T-554");
            //save books without author
            bookService.createBook(book1);
            bookService.createBook(book2);
            //assign author to books
//            authorFound.get().addBook(book1);
//            authorFound.get().addBook(book2);

            authorService.createAuthor(authorFound.get());

        }
    }

//    @Test
//    void createAuthorAndNewBooks(){
//        //create books
//        Book book1 = new Book("La comédie humaine 1",  462, 1842, "SSSGV-5-ERG5-6567");
//        Book book2 = new Book("La comédie humaine 2", 235, 1842, "56756-DFG2T-554");
//        //save books without author
//        bookService.createBook(book1);
//        bookService.createBook(book2);
//
//        LocalDate date3 = LocalDate.of (1822,1,4);
////        Author author1 = new Author("Honore","Balzec",date3 );
//
//        authorService.createAuthor(author1);
//
//        author1.addBook(book1);
//        author1.addBook(book2);
//
//        authorService.createAuthor(author1);
//    }



}