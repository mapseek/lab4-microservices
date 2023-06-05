package com.khr.accountservice.repository.impl;

import com.khr.accountservice.dto.CreditCardDto;
import com.khr.accountservice.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CreditCardRepositoryImpl implements CreditCardRepository {

    private final RestTemplate restTemplate;
    private static final String SERVICE_URL = "http://localhost:8082/api/v1/cards/";

    @Override
    public Optional<CreditCardDto> getCardById(Long cardId) {
        return Optional.ofNullable(restTemplate.getForObject(SERVICE_URL + cardId, CreditCardDto.class));
    }
}
