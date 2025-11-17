package com.thembelani.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(BookNotFoundException exc) {

        BookErrorResponse bookErrorResponse = new BookErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(bookErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler  //Default exception handling for any other EXCEPTION not being book not found exists
    public ResponseEntity<BookErrorResponse> handleException(Exception exc) {

        BookErrorResponse bookErrorResponse = new BookErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Invalid Request",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(bookErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
