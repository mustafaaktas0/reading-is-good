package com.example.readingisgood.dto;

import com.example.readingisgood.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CustomerDto {
    private String name;
    private String surname;

    public static CustomerDto converterCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .build();
    }
}
