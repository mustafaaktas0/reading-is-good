package com.example.readingisgood.controller;

import com.example.readingisgood.dto.AddBookRequest;
import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.service.BookService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/reading/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody AddBookRequest request) {
        return ResponseEntity.ok(bookService.addBook(request));
    }

    @GetMapping
    public ResponseEntity<List<Book>> addBook() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

}
