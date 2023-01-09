package com.example.readingisgood.controller;

import com.example.readingisgood.dto.AddBookRequest;
import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.dto.UpdateBookStock;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.model.BookStock;
import com.example.readingisgood.service.BookService;
import com.example.readingisgood.service.BookStockService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<Book>> getAllBook() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @PutMapping
    public void updateStock(@Valid @RequestBody UpdateBookStock request) {
        bookService.updateQuantityStock(request.getId(), request.getQuantityStock());
    }

    @PutMapping("/{id}")
    public void updateStock(@PathVariable String id) {
        bookService.updateQuantityStock(id);
    }

    @GetMapping("/bookStock")
    public ResponseEntity<List<BookStock>> getAllBookStock() {
        return ResponseEntity.ok(bookService.getAllBookStock());
    }

}
