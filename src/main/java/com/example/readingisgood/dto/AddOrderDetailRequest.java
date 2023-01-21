package com.example.readingisgood.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class AddOrderDetailRequest {
    @NotNull
    private String customerId;
    @NotNull
    private List<String> bookList;

}
