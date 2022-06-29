package com.example.library.model;

//https://projectlombok.org/features/all
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity(name="Book")
@Table(name="BOOK_TABLE")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BOOK_ID")
    private long bookId;
    @Column(name="BOOK_TITLE")
    private String title;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = @JoinColumn(name = "AUTHOR_FK"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_FK"))
    private List<Author> authors = new ArrayList<Author>();

    private int pages;
    @Column(name="PUBLISHED_YEAR")
    private int publishedYear;
    @Column(name="ISBN")
    private String isbn;

    //constructor without ID
    public Book(String title, int pages, int publishedYear, String isbn) {
        this.title = title;
        this.pages = pages;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
    }

    //constructor without ID
    public Book(String title, int pages, int publishedYear, String isbn, List<Author> authors) {
        this.title = title;
        this.pages = pages;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", publishedYear=" + publishedYear +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}