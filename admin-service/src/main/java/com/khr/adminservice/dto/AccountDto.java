package com.khr.adminservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class AccountDto {
    private Long id;
    private BigDecimal balance;
    private Long creditCardId;
    private Long clientId;
    private Boolean blocked;
}
