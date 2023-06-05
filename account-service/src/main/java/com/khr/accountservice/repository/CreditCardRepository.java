package com.khr.accountservice.repository;

import com.khr.accountservice.dto.CreditCardDto;

import java.util.Optional;

public interface CreditCardRepository {
    Optional<CreditCardDto> getCardById(Long cardId);
}
