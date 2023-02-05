package com.example.readingisgood.service;

import com.example.readingisgood.exception.BookNotFoundException;
import com.example.readingisgood.model.BookStock;
import com.example.readingisgood.repository.BookStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStockService {
    private final BookStockRepository bookStockRepository;

    public BookStockService(BookStockRepository bookStockRepository) {
        this.bookStockRepository = bookStockRepository;
    }

    public void addBookStock(String id) {
        BookStock newBookStock = BookStock.builder()
                .bookId(id)
                .quantityStock(1)
                .build();
        bookStockRepository.save(newBookStock);
    }

    public void updateBookStock(String bookId, int quantityStock) {
        BookStock bookStock = bookStockRepository.findBookByBookId(bookId);
        bookStock.setQuantityStock(quantityStock);
        bookStockRepository.save(bookStock);
    }

    public void updateBookStock(String bookId) {

        BookStock bookStock = bookStockRepository.findBookByBookId(bookId);
        int newQuentityStock = bookStock.getQuantityStock() + 1;
        bookStock.setQuantityStock(newQuentityStock);
        bookStockRepository.save(bookStock);
    }

    public void updateRentalBookStock(String bookId) throws Exception {

        BookStock bookStock = bookStockRepository.findBookByBookId(bookId);
        int newQuentityStock = bookStock.getQuantityStock() - 1;
        if (newQuentityStock >= 0) {
            bookStock.setQuantityStock(newQuentityStock);
            bookStockRepository.save(bookStock);
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }
    public List<BookStock> getAllBookStock() {

        return bookStockRepository.findAll();
    }

    protected BookStock findBookStockById(String id) {
        return bookStockRepository.findById(id)
                .orElseThrow(
                        () -> new BookNotFoundException("BookStock could not find by id:" + id));

    }
}
