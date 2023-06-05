package com.khr.userservice.repository;

import com.khr.userservice.dto.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Optional<AccountDto> findById(Long accountId);
    List<AccountDto> findAllByClient(Long clientId);
    AccountDto save(AccountDto account);
}
