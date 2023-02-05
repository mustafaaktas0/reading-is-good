package com.example.readingisgood.dto;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDateTime;

@Data
public class DateIntervalRequest {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @AssertTrue(message = "başlangıç tarihi bitiş tarihinden önce olmalıdır")
    public boolean isEndDateAfterStartDate() {
        return endDate.isAfter(startDate);
    }
}
