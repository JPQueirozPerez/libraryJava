package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.BookService;
import com.example.library.service.AuthorService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;


@Controller
@RequestMapping("/library")
public class LibraryWebController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @RequestMapping("/books")
    public String getWeb(Model containerToView, Model authorToView) {
        //
        containerToView.addAttribute("booksfromController",
                bookService.getAllBooks());
        authorToView.addAttribute("authorsfromController",
                authorService.getAllAuthors());
        return "showBooks";
    }

    @RequestMapping("/newBook")
    public String newBook(Model containerToView) {
        containerToView.addAttribute("authorsFromController",
                authorService.getAllAuthors());
        return "newBook";
    }

    @RequestMapping("/newAuthor")
    public String newAuthor() {
        return "newAuthor";
    }

    @RequestMapping("/addBook")
    public String addBook(@RequestParam("title") String title, @RequestParam("isbn") String isbn, @RequestParam("pages") int pages, @RequestParam("publishedYear") int publishedYear, @RequestParam("authorId") Author authorId) {

        Book book = new Book(title, pages, publishedYear, isbn, authorId);
        bookService.createBook(book);
        return "redirect:books";
    }

    @RequestMapping("/addAuthor")
    public String addAuthor(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("dob") String dob) throws ParseException {

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
        Author author = new Author(firstName, lastName, date);
        authorService.createAuthor(author);
        return "redirect:books#authorTable";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestParam("bookIdFromView") Long id) {
        //
        bookService.deleteBookById(id);
        return "redirect:books";
    }

    @RequestMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam("authorIdFromView") Long id) {
        //
        authorService.deleteAuthorById(id);
        return "redirect:books#authorTable";
    }

    @RequestMapping("/fakerbooks")
    public String createFakeBooks(@RequestParam("qtyBooks") int qty) {

        Faker faker = new Faker();

        for (int i = 0; i < qty; i++) {
            String title = faker.book().title();
            int pages = faker.random().nextInt(100, 1000);
            int year = faker.random().nextInt(1800, 2020);

            Book book = new Book(title, pages, year, "56756-DFG2T-55" + i);
            bookService.createBook(book);
        }

        return "redirect:books";

    }

    @RequestMapping("/fakerauthors")
    public String createFakeAuthors(@RequestParam("qtyAuthors") int qty) {

        Faker faker = new Faker();

        for (int i = 0; i < qty; i++) {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            int day = faker.random().nextInt(1, 28);
            int month = faker.random().nextInt(1, 12);
            int year = faker.random().nextInt(1750, 2000);
            LocalDate localDate = LocalDate.of(year, month, day);
            Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            Author author = new Author(firstName, lastName, date);
            authorService.createAuthor(author);
        }

        return "redirect:books#authorTable";

    }

    @RequestMapping("/detailBook")
    public String detailBook(@RequestParam("bookIdFromView") Long id, Model model) {
        model.addAttribute("book",
                bookService.findBookById(id).get());
        return "detailBook";
    }

    @RequestMapping("/detailAuthor")
    public String detailAuthor(@RequestParam("authorIdFromView") Long id, Model model) {
        model.addAttribute("author",
                authorService.findAuthorById(id).get());
        return "detailAuthor";
    }

    @RequestMapping("/updateBook")
    public String updateBook(@RequestParam("bookIdFromView") Long id, Model bookfromController, Model authorfromController) {
        bookfromController.addAttribute("bookfromController",
                bookService.findBookById(id).get());
        authorfromController.addAttribute("authorsfromController",
                authorService.getAllAuthors());
        return "updateBook";
    }

    @RequestMapping("/updateAuthor")
    public String updateAuthor(@RequestParam("authorIdFromView") Long id, Model authorfromController) {
        authorfromController.addAttribute("authorfromController",
                authorService.findAuthorById(id).get());
        return "updateAuthor";
    }

    @PostMapping("/replaceBook/{idFromView}")
    public String replaceBook(@PathVariable("idFromView") Long id, Book book, Author author,
                              Model bookfromController, Model authorfromController) {
        bookfromController.addAttribute("bookfromController",
                bookService.findBookById(id).get());
        authorfromController.addAttribute("authorfromController",
                authorService.getAllAuthors());
        Optional<Book> newBook = bookService.findBookById(id);
        if (newBook.isPresent()) {
            book = new Book(book.getBookId(), book.getTitle(), author, book.getPages(), book.getPublishedYear(), book.getIsbn());
            bookService.createBook(book);
            return "redirect:/library/books";
        } else return "error";

    }

    @PostMapping("/replaceAuthor/{idFromView}")
    public String replaceAuthor(@PathVariable("idFromView") Long id, Author author,
                                Model authorfromController) {

        authorfromController.addAttribute("authorfromController",
                authorService.findAuthorById(id).get());
        Optional<Author> newAuthor = authorService.findAuthorById(id);
        if (newAuthor.isPresent()) {
            authorService.createAuthor(author);
            return "redirect:/library/books#authorTable";
        } else return "error";

    }


}
