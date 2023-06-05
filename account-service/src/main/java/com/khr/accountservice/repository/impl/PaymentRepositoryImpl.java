package com.khr.accountservice.repository.impl;

import com.khr.accountservice.dto.PaymentDto;
import com.khr.accountservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final RestTemplate restTemplate;
    private static final String SERVICE_URL = "http://localhost:8083/api/v1/payments/";

    @Override
    public List<PaymentDto> findAllByAccountId(Long id) {
        return restTemplate.exchange(SERVICE_URL + "accounts/" + id, HttpMethod.GET,null,
                new ParameterizedTypeReference<List<PaymentDto>>() {}).getBody();
    }
}
