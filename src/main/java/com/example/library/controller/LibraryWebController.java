package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import com.example.library.service.AuthorService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;


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
                AuthorRestController.getAllAuthors());
        return "newBook";
    }

    @RequestMapping("/addBook")
    public String addBook(@RequestParam("title") String title, @RequestParam("isbn") String isbn, @RequestParam("pages") int pages, @RequestParam("publishedYear") int publishedYear) {

        Book book = new Book(title, pages, publishedYear, isbn);
        bookService.createBook(book);
        return "redirect:books";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestParam("bookIdFromView") Long id) {
        //
        bookService.deleteBookById(id);
        return "redirect:books";
    }

    @RequestMapping("/fakerbooks")
    public String createFakeBooks( @RequestParam("qtyBooks") int qty ){

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
    public String createFakeAuthors( @RequestParam("qtyAuthors") int qty ){

        Faker faker = new Faker();

        for (int i = 0; i < qty; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            int day = faker.random().nextInt(1, 28);
            int month = faker.random().nextInt(1, 12);
            int year = faker.random().nextInt(1750, 2000);
            LocalDate date = LocalDate.of (year,month,day);

            Author author = new Author(firstName, lastName, date );
            authorService.createAuthor(author);
        }

        return "redirect:books";

    }

    @RequestMapping("/detailBook")
    public String detailBook(@RequestParam("bookIdFromView") Long id, Model model) {
        model.addAttribute("book",
                bookService.findBookById(id).get());
        return "detailBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(@RequestParam("bookIdFromView") Long id, Model bookfromController, Model authorFromController) {
        bookfromController.addAttribute("bookfromController",
                bookService.findBookById(id).get());
        authorFromController.addAttribute("authorfromController",
                authorService.getAllAuthors());
        return "updateBook";
    }

    @RequestMapping("/replaceBook")
    public String replaceBook(@RequestParam("bookId") Long id,
                              @RequestParam("title") String title,
                              @RequestParam("isbn") String isbn,
                              @RequestParam("pages") int pages,
                              @RequestParam("author") Author author,
                              @RequestParam("publishedYear") int publishedYear,
                              Model bookfromController, Model authorfromController) {
        bookfromController.addAttribute("bookfromController",
                bookService.findBookById(id).get());
        authorfromController.addAttribute("authorfromController",
                authorService.getAllAuthors());
        Book book = new Book(id,title,author,pages,publishedYear,isbn );
        bookService.updateBook(book);
        return "redirect:books";
    }


}
