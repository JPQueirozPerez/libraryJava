package com.example.library.model;

//https://projectlombok.org/features/all
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity(name="Author")
@Table(name="AUTHOR_TABLE")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="AUTHOR_ID")
    private long authorId;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name = "DATE_OF_BIRTH", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @ManyToMany
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns=@JoinColumn(name = "BOOK_FK"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_FK"))
    private List<Book> books = new ArrayList<Book>();

    //constructor without ID
    public Author(String firstName, String lastName, Date dob){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    //method to add books to books
//    public void addBook(Book book) {
//        this.getBooks().add(book);
//        if (book.getAuthors() != null) book.getAuthors().getBooks().remove(book);
//        book.setAuthors((List<Author>) this);
//    }

    public String authorName(){
        return firstName+" "+lastName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", books=" + books +
                '}';
    }
}