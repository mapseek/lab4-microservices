package com.khr.paymentservice.domain;

import com.khr.paymentservice.dto.PaymentDto;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "client_id")
    private Long clientId;

    public PaymentDto toDto(){
        return new PaymentDto()
                .setId(id)
                .setAmount(amount)
                .setDate(date)
                .setAccountId(accountId)
                .setClientId(clientId);
    }
}
