package com.example.book.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReturnBookResponse {
    private Long userId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private BigDecimal sumPrice;
    private BigDecimal latePaymentFee;

}
