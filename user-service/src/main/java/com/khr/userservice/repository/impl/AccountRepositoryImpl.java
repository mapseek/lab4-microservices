package com.khr.userservice.repository.impl;

import com.khr.userservice.repository.AccountRepository;
import com.khr.userservice.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final RestTemplate restTemplate;
    private static final String SERVICE_URL = "http://localhost:8081/api/v1/accounts/";

    @Override
    public Optional<AccountDto> findById(Long accountId) {
        return Optional.ofNullable(restTemplate.getForObject(SERVICE_URL + accountId, AccountDto.class));
    }

    @Override
    public List<AccountDto> findAllByClient(Long clientId) {
        return restTemplate.exchange(SERVICE_URL + "clients/" + clientId, HttpMethod.GET,null,
                new ParameterizedTypeReference<List<AccountDto>>() {}).getBody();
    }

    @Override
    public AccountDto save(AccountDto account) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccountDto> requestEntity = new HttpEntity<>(account, headers);
        return restTemplate.exchange(SERVICE_URL + account.getId(), HttpMethod.PATCH, requestEntity, AccountDto.class).getBody();
    }
}
