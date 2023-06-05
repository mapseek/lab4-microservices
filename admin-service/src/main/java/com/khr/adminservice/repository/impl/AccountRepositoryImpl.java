package com.khr.adminservice.repository.impl;

import com.khr.adminservice.dto.AccountDto;
import com.khr.adminservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

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
    public AccountDto save(AccountDto account) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccountDto> requestEntity = new HttpEntity<>(account, headers);
        return restTemplate.exchange(SERVICE_URL + account.getId(), HttpMethod.PATCH, requestEntity, AccountDto.class).getBody();
    }
}
