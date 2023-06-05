package com.skyba.cardservice.service.impl;

import com.skyba.cardservice.domain.CreditCard;
import com.skyba.cardservice.dto.ClientDto;
import com.skyba.cardservice.dto.CreditCardDto;
import com.skyba.cardservice.repository.ClientRepository;
import com.skyba.cardservice.repository.CreditCardRepository;
import com.skyba.cardservice.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final ClientRepository clientRepository;

    @Override
    public List<CreditCardDto> getAll() {
        return creditCardRepository.findAll().stream().map(CreditCard::toDto).collect(Collectors.toList());
    }

    @Override
    public CreditCardDto getById(Long id) {
        return creditCardRepository.findById(id).orElseThrow(()-> new RuntimeException("Credit card is not found")).toDto();
    }

    @Override
    public CreditCardDto createCreditCard(CreditCardDto createBody) {
        CreditCard creditCard = createBody.toDomain();
        ClientDto client = clientRepository.findById(createBody.getClientId()).orElseThrow(()-> new RuntimeException("Client is not found"));
        creditCard.setClientId(client.getId());
        CreditCard stored = creditCardRepository.save(creditCard);
        return stored.toDto();
    }

    @Override
    public CreditCardDto updateCreditCard(Long id, CreditCardDto updateBody) {
        CreditCard persisted = creditCardRepository.findById(id).orElseThrow(()-> new RuntimeException("Credit card is not found"));
        LocalDate expiryDate = updateBody.getExpiryDate();
        if (expiryDate != null) persisted.setExpiryDate(expiryDate);

        String cvv = updateBody.getCvv();
        if (cvv != null) persisted.setCvv(cvv);

        CreditCard stored = creditCardRepository.save(persisted);
        return stored.toDto();
    }

    @Override
    public void deleteCreditCard(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(()-> new RuntimeException("Credit card is not found"));
        creditCardRepository.delete(creditCard);
    }

    @Override
    public List<CreditCardDto> getAllByClientId(Long clientId) {
        return creditCardRepository.findAllByClientId(clientId).stream().map(CreditCard::toDto).collect(Collectors.toList());
    }
}
