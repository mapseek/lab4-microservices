package com.khr.userservice.service;

import com.khr.userservice.dto.*;
import com.skyba.userservice.dto.*;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {
    List<ClientDto> getAll();

    ClientDto getById(Long id);

    ClientDto createClient(ClientDto createBody);

    ClientDto updateClient(Long id, ClientDto updateBody);

    void deleteClient(Long id);

    AccountDto blockAccount(Long clientId, Long accountId);

    AccountDto topUpAccount(Long clientId, Long accountId, BigDecimal amount);

    PaymentDto makePayment(Long clientId, PaymentRequest request);

    List<PaymentDto> getAllPaymentsByClient(Long clientId);

    List<AccountDto> getAllAccountsByClient(Long clientId);

    List<CreditCardDto> getAllCardsByClient(Long id);
}
