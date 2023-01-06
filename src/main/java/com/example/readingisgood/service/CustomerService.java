package com.example.readingisgood.service;

import com.example.readingisgood.dto.CustomerDto;
import com.example.readingisgood.dto.RegisterCustomerRequest;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto registerCustomer(RegisterCustomerRequest request) {

        Customer customer = Customer.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .build();
        return CustomerDto.converterCustomerDto(customerRepository.save(customer));
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
}
