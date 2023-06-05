package com.khr.accountservice.service.impl;

import com.khr.accountservice.dto.CreditCardDto;
import com.khr.accountservice.dto.PaymentDto;
import com.khr.accountservice.domain.Account;
import com.khr.accountservice.dto.AccountDto;
import com.khr.accountservice.repository.AccountRepository;
import com.khr.accountservice.repository.CreditCardRepository;
import com.khr.accountservice.repository.PaymentRepository;
import com.khr.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CreditCardRepository creditCardRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll().stream().map(Account::toDto).collect(Collectors.toList());
    }

    @Override
    public AccountDto getById(Long id) {
        return accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account is not found")).toDto();
    }

    @Override
    public AccountDto createAccount(AccountDto createBody) {
        CreditCardDto creditCard = creditCardRepository.getCardById(createBody.getCreditCardId()).orElseThrow(()-> new RuntimeException("Credit card is not found"));
        Account account = new Account()
                .setBalance(BigDecimal.ZERO)
                .setBlocked(false)
                .setCreditCardId(creditCard.getId())
                .setClientId(creditCard.getClientId());

        Account stored = accountRepository.save(account);
        return stored.toDto();
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto updateBody) {
        Account persisted = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account is not found"));
        Boolean blocked = updateBody.getBlocked();
        if (blocked!= null) persisted.setBlocked(blocked);

        BigDecimal balance = updateBody.getBalance();
        if(balance != null) persisted.setBalance(balance);

        Account stored = accountRepository.save(persisted);
        return stored.toDto();
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account is not found"));
        accountRepository.delete(account);
    }

    @Override
    public List<PaymentDto> getAllByAccountId(Long id) {
        return paymentRepository.findAllByAccountId(id);
    }

    @Override
    public List<AccountDto> getAllByClientId(Long clientId) {
        return accountRepository.findAllByClientId(clientId).stream().map(Account::toDto).collect(Collectors.toList());
    }
}
