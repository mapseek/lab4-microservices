package com.khr.accountservice.domain;

import com.khr.accountservice.dto.AccountDto;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "card_id")
    private Long creditCardId;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "blocked")
    private Boolean blocked;

    public AccountDto toDto(){
        return new AccountDto()
                .setId(id)
                .setBalance(balance)
                .setBlocked(blocked)
                .setCreditCardId(creditCardId)
                .setClientId(clientId);
    }
}
