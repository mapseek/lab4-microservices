package com.khr.userservice.repository;

import com.khr.userservice.dto.CreditCardDto;

import java.util.List;

public interface CreditCardRepository {
    List<CreditCardDto> findAllByClientId(Long id);
}
