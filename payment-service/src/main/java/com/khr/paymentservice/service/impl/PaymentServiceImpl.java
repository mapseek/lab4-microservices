package com.khr.paymentservice.service.impl;

import com.khr.paymentservice.repository.ClientRepository;
import com.khr.paymentservice.repository.PaymentRepository;
import com.khr.paymentservice.domain.Payment;
import com.khr.paymentservice.dto.AccountDto;
import com.khr.paymentservice.dto.ClientDto;
import com.khr.paymentservice.dto.PaymentDto;
import com.khr.paymentservice.repository.AccountRepository;
import com.khr.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    @Override
    public List<PaymentDto> getAll() {
        return paymentRepository.findAll().stream().map(Payment::toDto).collect(Collectors.toList());
    }

    @Override
    public PaymentDto getById(Long id) {
        return paymentRepository.findById(id).orElseThrow(()-> new RuntimeException("Payment is not found")).toDto();
    }

    @Override
    public PaymentDto makePayment(Long accountId, BigDecimal amount) {
        AccountDto account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account is not found"));
        ClientDto clientDto = clientRepository.findById(account.getClientId()).orElseThrow(()-> new RuntimeException("Client is not found"));
        BigDecimal balance = account.getBalance();
        if (balance.compareTo(amount) < 0) throw new RuntimeException("Account balance is less than payment amount");

        account.setBalance(balance.subtract(amount));
        AccountDto storedAccount = accountRepository.save(account);
        Payment payment = new Payment()
                .setAccountId(storedAccount.getId())
                .setClientId(clientDto.getId())
                .setAmount(amount)
                .setDate(LocalDate.now());

        Payment stored = paymentRepository.save(payment);
        return stored.toDto();
    }

    @Override
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment is not found"));
        paymentRepository.delete(payment);
    }

    @Override
    public List<PaymentDto> getAllByClientId(Long clientId) {
        return paymentRepository.findAllByClientId(clientId).stream().map(Payment::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PaymentDto> getAllByAccountId(Long accountId) {
        return paymentRepository.findAllByAccountId(accountId).stream().map(Payment::toDto).collect(Collectors.toList());
    }
}
