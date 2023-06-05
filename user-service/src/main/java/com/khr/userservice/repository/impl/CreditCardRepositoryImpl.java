package com.khr.userservice.repository.impl;

import com.khr.userservice.repository.CreditCardRepository;
import com.khr.userservice.dto.CreditCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CreditCardRepositoryImpl implements CreditCardRepository {

    private final RestTemplate restTemplate;
    private static final String SERVICE_URL = "http://localhost:8082/api/v1/cards/";

    @Override
    public List<CreditCardDto> findAllByClientId(Long id) {
        return restTemplate.exchange(SERVICE_URL + "clients/" + id, HttpMethod.GET,null,
                new ParameterizedTypeReference<List<CreditCardDto>>() {}).getBody();
    }
}
