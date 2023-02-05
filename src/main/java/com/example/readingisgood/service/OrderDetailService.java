package com.example.readingisgood.service;

import com.example.readingisgood.dto.AddOrderDetailRequest;
import com.example.readingisgood.dto.DateIntervalRequest;
import com.example.readingisgood.dto.OrderDetailDto;
import com.example.readingisgood.exception.OrderDetailNotFoundException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.model.OrderDetail;
import com.example.readingisgood.repository.OrderDetailRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerService customerService;
    private final BookService bookService;
    private final BookStockService bookStockService;

    public OrderDetailService(OrderDetailRepository orderDetailRepository, CustomerService customerService, BookService bookService, BookStockService bookStockService) {
        this.orderDetailRepository = orderDetailRepository;
        this.customerService = customerService;
        this.bookService = bookService;
        this.bookStockService = bookStockService;
    }

    public List<OrderDetail> getAllOrderDetail() {
        return orderDetailRepository.findAll();
    }
    @SneakyThrows
    public OrderDetailDto addOrder(AddOrderDetailRequest addOrderDetailRequest) {

        Customer customer = findCustomerById(addOrderDetailRequest.getCustomerId());
        List<Book> bookList = addOrderDetailRequest.getBookList()
                .stream()
                .map(id -> findBookById(id))
                .collect(Collectors.toList());


        OrderDetail orderDetail = OrderDetail.builder()
                .bookList(addOrderDetailRequest.getBookList())
                .customerId(addOrderDetailRequest.getCustomerId())
                .createDateTime(LocalDateTime.now())
                .totalPrice(getCalculatePrice(addOrderDetailRequest.getBookList()))
                .build();

        updateStock(bookList);
        return OrderDetailDto.converterOrderDetailDto(orderDetailRepository.save(orderDetail));
    }

    public List<OrderDetail> getAllOrder() {
        return orderDetailRepository.findAll();
    }

    private Double getCalculatePrice(List<String> bookList) {
        return (double) bookList.size() * 10;
    }

    @SneakyThrows
    private Customer findCustomerById(String id) {
        return customerService.findCustomerById(id);
    }

    @SneakyThrows
    private Book findBookById(String id) {
        return bookService.findBookById(id);
    }

    private void updateStock(List<Book> bookList) {
        for (Book book : bookList) {
            try {
                bookStockService.updateRentalBookStock(book.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SneakyThrows
    public OrderDetailDto getOrderDetailById(String id) {

        return OrderDetailDto.converterOrderDetailDto(findOrderDetailById(id));
    }

    protected OrderDetail findOrderDetailById(String id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(
                        () -> new OrderDetailNotFoundException("Order detail could not find by id:" + id));

    }

    public List<OrderDetailDto> getOrderDetailsByDateInterval(DateIntervalRequest request) {
        List<OrderDetail> orders = orderDetailRepository.findBycreateDateTimeBetween(request.getStartDate(), request.getEndDate());
        return orders.stream()
                .map(OrderDetailDto::converterOrderDetailDto)
                .collect(Collectors.toList());
    }
}
