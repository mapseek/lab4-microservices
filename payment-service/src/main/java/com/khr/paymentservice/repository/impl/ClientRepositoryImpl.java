package com.khr.paymentservice.repository.impl;

import com.khr.paymentservice.repository.ClientRepository;
import com.khr.paymentservice.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final RestTemplate restTemplate;
    private static final String SERVICE_URL = "http://localhost:8080/api/v1/clients/";

    @Override
    public Optional<ClientDto> findById(Long clientId) {
        return Optional.ofNullable(restTemplate.getForObject(SERVICE_URL + clientId, ClientDto.class));
    }
}
