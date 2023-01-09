package com.example.readingisgood.service;

import com.example.readingisgood.dto.AddBookRequest;
import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.exception.BookNotFoundException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.model.BookStock;
import com.example.readingisgood.repository.BookRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookStockService bookStockService;

    public BookService(BookRepository bookRepository, BookStockService bookStockService) {
        this.bookRepository = bookRepository;
        this.bookStockService = bookStockService;
    }

    public BookDto addBook(AddBookRequest request) {
        Book book = Book.builder()
                .author(request.getAuthor())
                .bookYear(request.getBookYear())
                .pressName(request.getPressName())
                .title(request.getTitle())
                .bookYear(request.getBookYear())
                .build();
        Book savedBook = bookRepository.save(book);
        bookStockService.addBookStock(savedBook.getId());
        return BookDto.converterBookDto(savedBook);
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @SneakyThrows
    public void updateQuantityStock(String id, int quantityStock) {
        Book book = findBookById(id);
        bookStockService.updateBookStock(book.getId(), quantityStock);

    }

    @SneakyThrows
    public void updateQuantityStock(String id) {
        Book book = findBookById(id);
        bookStockService.updateBookStock(book.getId());
    }

    protected Book findBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(
                        () -> new BookNotFoundException("Book could not find by id:" + id));

    }

    public List<BookStock> getAllBookStock() {
        return bookStockService.getAllBookStock();
    }
}
