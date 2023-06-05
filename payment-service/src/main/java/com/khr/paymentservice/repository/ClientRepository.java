package com.khr.paymentservice.repository;

import com.khr.paymentservice.dto.ClientDto;

import java.util.Optional;

public interface ClientRepository {
    Optional<ClientDto> findById(Long clientId);
}
