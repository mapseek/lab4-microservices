package com.khr.paymentservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class AccountDto {
    private Long id;
    private BigDecimal balance;
    private Long creditCardId;
    private Long clientId;
    private Boolean blocked;
}
