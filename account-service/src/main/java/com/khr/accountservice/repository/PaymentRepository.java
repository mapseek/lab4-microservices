package com.khr.accountservice.repository;

import com.khr.accountservice.dto.PaymentDto;

import java.util.List;

public interface PaymentRepository {
    List<PaymentDto> findAllByAccountId(Long id);
}
