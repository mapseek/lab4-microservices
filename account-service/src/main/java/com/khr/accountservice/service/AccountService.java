package com.khr.accountservice.service;

import com.khr.accountservice.dto.AccountDto;
import com.khr.accountservice.dto.PaymentDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAll();

    AccountDto getById(Long id);

    AccountDto createAccount(AccountDto createBody);

    AccountDto updateAccount(Long id, AccountDto updateBody);

    void deleteAccount(Long id);

    List<PaymentDto> getAllByAccountId(Long id);

    List<AccountDto> getAllByClientId(Long clientId);
}
