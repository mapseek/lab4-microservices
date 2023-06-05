package com.khr.paymentservice.repository;

import com.khr.paymentservice.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByClientId(Long clientId);
    List<Payment> findAllByAccountId(Long accountId);
}
