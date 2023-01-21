package com.example.readingisgood.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DateIntervalRequest {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
