package com.khr.adminservice.repository;

import com.khr.adminservice.dto.AccountDto;

import java.util.Optional;

public interface AccountRepository {
    Optional<AccountDto> findById(Long accountId);
    AccountDto save(AccountDto account);
}
