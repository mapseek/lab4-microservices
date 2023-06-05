package com.khr.userservice.repository.impl;

import com.khr.userservice.dto.PaymentDto;
import com.khr.userservice.dto.PaymentRequest;
import com.khr.userservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final RestTemplate restTemplate;
    private static final String SERVICE_URL = "http://localhost:8083/api/v1/payments/";

    @Override
    public List<PaymentDto> findAllByClient(Long clientId) {
        return restTemplate.exchange(SERVICE_URL + "clients/" + clientId, HttpMethod.GET,null,
                new ParameterizedTypeReference<List<PaymentDto>>() {}).getBody();
    }

    @Override
    public PaymentDto makePayment(PaymentRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentRequest> requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(SERVICE_URL, HttpMethod.POST, requestEntity, PaymentDto.class).getBody();
    }
}
