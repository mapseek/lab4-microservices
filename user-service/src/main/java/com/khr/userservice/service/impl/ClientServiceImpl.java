package com.khr.userservice.service.impl;

import com.khr.userservice.domain.Client;
import com.khr.userservice.dto.*;
import com.khr.userservice.repository.AccountRepository;
import com.khr.userservice.repository.ClientRepository;
import com.khr.userservice.repository.CreditCardRepository;
import com.khr.userservice.repository.PaymentRepository;
import com.khr.userservice.service.ClientService;
import com.skyba.userservice.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CreditCardRepository creditCardRepository;
    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public List<ClientDto> getAll() {
        return clientRepository.findAll().stream().map(Client::toDto).collect(Collectors.toList());
    }

    @Override
    public ClientDto getById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Client is not found"));
        return client.toDto();
    }

    @Override
    public List<CreditCardDto> getAllCardsByClient(Long id) {
        return creditCardRepository.findAllByClientId(id);
    }

    @Override
    public ClientDto createClient(ClientDto createBody) {
        Client client = createBody.toDomain();
        Client stored = clientRepository.save(client);
        return stored.toDto();
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto updateBody) {
        Client persisted = clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Client is not found"));

        String email = updateBody.getEmail();
        if (email != null) persisted.setEmail(email);

        String firstName = updateBody.getFirstName();
        if (firstName != null) persisted.setFirstName(firstName);

        String lastName = updateBody.getLastName();
        if (lastName != null) persisted.setLastName(lastName);

        Client stored = clientRepository.save(persisted);
        return stored.toDto();
    }

    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Client is not found"));
        clientRepository.delete(client);
    }

    @Override
    public AccountDto blockAccount(Long clientId, Long accountId) {
        Client client = clientRepository.findById(clientId).orElseThrow(()-> new RuntimeException("Client is not found"));
        AccountDto account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account is not found"));
        if (account.getClientId() != client.getId()) throw new RuntimeException("Wrong account for client");
        if (account.getBlocked() == true) throw new RuntimeException("Account already blocked");
        account.setBlocked(true);
        AccountDto stored = accountRepository.save(account);
        return stored;
    }

    @Override
    public AccountDto topUpAccount(Long clientId, Long accountId, BigDecimal amount) {
        Client client = clientRepository.findById(clientId).orElseThrow(()-> new RuntimeException("Client is not found"));
        AccountDto account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account is not found"));
        if (account.getClientId() != client.getId()) throw new RuntimeException("Wrong account for client");
        if (account.getBlocked() == true) throw new RuntimeException("Account blocked and can't be topped up");

        account.setBalance(account.getBalance().add(amount));
        AccountDto stored = accountRepository.save(account);
        return stored;
    }

    @Override
    public PaymentDto makePayment(Long clientId, PaymentRequest request) {
        Client client = clientRepository.findById(clientId).orElseThrow(()-> new RuntimeException("Client is not found"));
        AccountDto account = accountRepository.findById(request.getAccountId()).orElseThrow(() -> new RuntimeException("Account is not found"));
        if (account.getClientId() != client.getId()) throw new RuntimeException("Wrong account for client");
        if (account.getBlocked() == true) throw new RuntimeException("Account is blocked");
        PaymentDto stored = paymentRepository.makePayment(request);
        return stored;
    }

    @Override
    public List<PaymentDto> getAllPaymentsByClient(Long clientId) {
        return paymentRepository.findAllByClient(clientId);
    }

    @Override
    public List<AccountDto> getAllAccountsByClient(Long clientId) {
        return accountRepository.findAllByClient(clientId);
    }
}
