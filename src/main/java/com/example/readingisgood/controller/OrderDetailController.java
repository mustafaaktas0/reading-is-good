package com.example.readingisgood.controller;

import com.example.readingisgood.dto.AddOrderDetailRequest;
import com.example.readingisgood.dto.DateIntervalRequest;
import com.example.readingisgood.dto.OrderDetailDto;
import com.example.readingisgood.model.OrderDetail;
import com.example.readingisgood.service.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/reading/order-detail")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    public ResponseEntity<OrderDetailDto> addOrderDetail(@Valid @RequestBody AddOrderDetailRequest request) {
        return ResponseEntity.ok(orderDetailService.addOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        return ResponseEntity.ok(orderDetailService.getAllOrder());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDto> getOrderDetailById(@PathVariable String id) {
        return ResponseEntity.ok(orderDetailService.getOrderDetailById(id));
    }

    @PostMapping("/date")
    public ResponseEntity<List<OrderDetailDto>> getOrderDetailsByDateInterval(@RequestBody DateIntervalRequest request) {
        return ResponseEntity.ok(orderDetailService.getOrderDetailsByDateInterval(request));
    }
}
