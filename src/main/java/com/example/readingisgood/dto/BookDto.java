package com.example.readingisgood.dto;

import com.example.readingisgood.model.Book;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {
    private String title;
    private int bookYear;
    private String author;
    private String pressName;
    private String isbn;

    public static BookDto converterBookDto(Book book) {
        return BookDto.builder()
                .author(book.getAuthor())
                .bookYear(book.getBookYear())
                .pressName(book.getPressName())
                .title(book.getTitle())
                .bookYear(book.getBookYear())
                .build();
    }
}
