package com.example.readingisgood.service;

import com.example.readingisgood.dto.AddOrderDetailRequest;
import com.example.readingisgood.dto.DateIntervalRequest;
import com.example.readingisgood.dto.OrderDetailDto;
import com.example.readingisgood.exception.BookNotFoundException;
import com.example.readingisgood.exception.OrderDetailNotFoundException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.model.OrderDetail;
import com.example.readingisgood.repository.OrderDetailRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerService customerService;
    private final BookService bookService;

    public OrderDetailService(OrderDetailRepository orderDetailRepository, CustomerService customerService, BookService bookService) {
        this.orderDetailRepository = orderDetailRepository;
        this.customerService = customerService;
        this.bookService = bookService;
    }

    public List<OrderDetail> getAllOrderDetail() {
        return orderDetailRepository.findAll();
    }

    @SneakyThrows
    public OrderDetailDto addOrder(AddOrderDetailRequest addOrderDetailRequest) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tenDaysLater = now.plus(10, ChronoUnit.DAYS);
        System.out.println("10 gün sonrasının tarihi ve saati: " + tenDaysLater);
        System.out.println("10 gün sonrasının tarihi: " + tenDaysLater);
        Customer customer = findCustomerById(addOrderDetailRequest.getCustomerId());
        List<Book> bookList = addOrderDetailRequest.getBookList()
                .stream()
                .map(id -> findBookById(id))
                .collect(Collectors.toList());


        OrderDetail orderDetail = OrderDetail.builder()
                .bookList(addOrderDetailRequest.getBookList())
                .customerId(addOrderDetailRequest.getCustomerId())
                .createDateTime(now)
                .endDate(tenDaysLater)
                .totalPrice(getCalculatePrice(addOrderDetailRequest.getBookList()))
                .build();
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


    private void updateStock() {


    }

    @SneakyThrows
    public OrderDetailDto getOrderDetailById(String id) {

        return OrderDetailDto.converterOrderDetailDto(findOrderDetailById(id));
    }

    protected OrderDetail findOrderDetailById(String id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(
                        () -> new OrderDetailNotFoundException("Order detail ould not find by id:" + id));

    }

    public List<OrderDetailDto> getOrderDetailsByDateInterval(DateIntervalRequest request) {

        return getOrdersByDateInterval(request.getStartDate(), request.getEndDate())
                .stream()
                .map(orderDetail -> OrderDetailDto.converterOrderDetailDto(orderDetail))
                .collect(Collectors.toList());

    }

    public List<OrderDetail> getOrdersByDateInterval(LocalDateTime startDate, LocalDateTime endDate) {
        return orderDetailRepository.findBycreateDateTimeBetween(startDate, endDate);
    }
}
