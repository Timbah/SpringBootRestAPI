package com.thembelani.books.controller;

//First part of the application that will consume the API endpoint,
// so we know what is sent in and will return data to the client

import com.thembelani.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books") //Adds /api/books to every endppint inside this java file
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

    @GetMapping("/hello")  //no path specified, so this means at the root path, the below method will be called
    public String firstAPI() {
        return "Hello Thembelani!";
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {

        if (category == null) {
            return books;
        }

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @GetMapping("/{title}") //THe parameter doesn't necessary need to be at the end.
    // i.e. something like /api/books/{title}/findByTitle would work
    public Book getBookByTitle(@PathVariable String title) {

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    @PostMapping
    public void createBook(@RequestBody Book newBook) {

        boolean isNewBook = books.stream()
                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));

        if (isNewBook) {
            books.add(newBook);
        }
    }

    @PutMapping("/{title}")
    public void updateBook(@PathVariable String title, @RequestBody Book updatedBook) {

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase((title))) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    @DeleteMapping("/{title}")
    public void deleteBook(@PathVariable String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }
}
