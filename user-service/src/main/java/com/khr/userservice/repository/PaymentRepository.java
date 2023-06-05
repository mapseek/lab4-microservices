package com.khr.userservice.repository;

import com.khr.userservice.dto.PaymentDto;
import com.khr.userservice.dto.PaymentRequest;

import java.util.List;

public interface PaymentRepository {
    List<PaymentDto> findAllByClient(Long clientId);

    PaymentDto makePayment(PaymentRequest request);
}
