package com.example.readingisgood.service;

import com.example.readingisgood.dto.AddBookRequest;
import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto addBook(AddBookRequest request) {
        Book book = Book.builder()
                .author(request.getAuthor())
                .bookYear(request.getBookYear())
                .pressName(request.getPressName())
                .title(request.getTitle())
                .bookYear(request.getBookYear())
                .build();
        return BookDto.converterBookDto(bookRepository.save(book));
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }
}
