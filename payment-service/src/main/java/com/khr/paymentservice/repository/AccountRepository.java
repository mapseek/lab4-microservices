package com.khr.paymentservice.repository;

import com.khr.paymentservice.dto.AccountDto;

import java.util.Optional;

public interface AccountRepository {
    Optional<AccountDto> findById(Long accountId);
    AccountDto save(AccountDto account);
}
