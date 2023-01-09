package com.example.readingisgood.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UpdateBookStock {
    @NotBlank
    private String id;
    @Min(1)
    private int quantityStock;
}
