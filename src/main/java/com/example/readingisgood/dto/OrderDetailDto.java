package com.example.readingisgood.dto;

import com.example.readingisgood.model.OrderDetail;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDetailDto {

    private String customerId;
    private List<String> bookList;
    private LocalDateTime createDateTime;
    private LocalDateTime endDate;

    public static OrderDetailDto converterOrderDetailDto(OrderDetail orderDetail) {
        return OrderDetailDto.builder()
                .customerId(orderDetail.getCustomerId())
                .bookList(orderDetail.getBookList())
                .createDateTime(orderDetail.getCreateDateTime())
                .endDate(orderDetail.getEndDate())
                .build();
    }
}
