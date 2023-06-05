package com.skyba.cardservice.repository;

import com.skyba.cardservice.dto.ClientDto;

import java.util.Optional;

public interface ClientRepository {
    Optional<ClientDto> findById(Long clientId);
}
