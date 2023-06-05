package com.skyba.cardservice.service;

import com.skyba.cardservice.dto.CreditCardDto;

import java.util.List;

public interface CreditCardService {
    List<CreditCardDto> getAll();

    CreditCardDto getById(Long id);

    CreditCardDto createCreditCard(CreditCardDto createBody);

    CreditCardDto updateCreditCard(Long id, CreditCardDto updateBody);

    void deleteCreditCard(Long id);

    List<CreditCardDto> getAllByClientId(Long clientId);
}
