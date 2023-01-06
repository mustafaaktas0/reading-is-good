package com.example.readingisgood.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class RegisterCustomerRequest {
    @NotEmpty
    private String name;
    @NotBlank
    private String surname;
}
