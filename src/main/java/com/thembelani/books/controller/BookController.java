package com.thembelani.books.controller;

//First part of the application that will consume the API endpoint,
// so we know what is sent in and will return data to the client

import com.thembelani.books.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        initializeBooks();
    }

    private void initializeBooks() {

        books.addAll(List.of(
                new Book("Title One", "Author One", "Science"),
                new Book("Title Two", "Author One", "Science"),
                new Book("Title Three", "Author One", "History"),
                new Book("Title Four", "Author One", "Math"),
                new Book("Title Five", "Author One", "Math"),
                new Book("Title Six", "Author One", "Math")
        ));
    }

    @GetMapping  //no path specified, so this means at the root path, the below method will be called
    public String firstAPI() {
        return "Hello Thembelani!";
    }

    @GetMapping("/api")
    public String firstAPIPath() {
        return "Thembelani Ngema!";
    }

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return books;
    }

    @GetMapping("/api/books/{title}") //THe parameter doesn't necessary need to be at the end.
    // i.e. something like /api/books/{title}/findByTitle would work
    public Book getBookByTitle(@PathVariable String title) {

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}
