package com.khr.accountservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class CreditCardDto {
    private Long id;
    private String number;
    private LocalDate expiryDate;
    private String cvv;
    private Long clientId;
}
