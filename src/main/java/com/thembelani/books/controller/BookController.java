package com.thembelani.books.controller;

//First part of the application that will consume the API endpoint,
// so we know what is sent in and will return data to the client

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping  //no path specified, so this means at the root path, the below method will be called
    public String firstAPI() {
        return "Hello Thembelani!";
    }
}
