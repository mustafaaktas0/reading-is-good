package com.example.readingisgood.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddBookRequest {

    private String title;
    private int bookYear;
    private String author;
    private String pressName;
    private String isbn;
}
