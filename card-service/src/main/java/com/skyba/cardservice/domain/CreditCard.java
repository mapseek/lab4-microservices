package com.skyba.cardservice.domain;

import com.skyba.cardservice.dto.CreditCardDto;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    @Column(name = "cvv")
    private String cvv;
    @Column(name = "client_id")
    private Long clientId;

    public CreditCardDto toDto() {
        return new CreditCardDto()
                .setId(id)
                .setClientId(clientId)
                .setNumber(number)
                .setCvv(cvv)
                .setExpiryDate(expiryDate);
    }
}
