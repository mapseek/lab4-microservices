package com.skyba.cardservice.controller;

import com.skyba.cardservice.dto.CreditCardDto;
import com.skyba.cardservice.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CreditCardController {

    private final CreditCardService service;

    @GetMapping
    public List<CreditCardDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CreditCardDto getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/clients/{clientId}")
    public List<CreditCardDto> getAllByClientId(@PathVariable Long clientId){
        return service.getAllByClientId(clientId);
    }

    @PostMapping
    public CreditCardDto createCreditCard(@RequestBody CreditCardDto createBody){
        return service.createCreditCard(createBody);
    }

    @PatchMapping("/{id}")
    public CreditCardDto updateCreditCard(@PathVariable Long id,@RequestBody CreditCardDto updateBody){
        return service.updateCreditCard(id,updateBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable Long id){
        service.deleteCreditCard(id);
        return ResponseEntity.noContent().build();
    }
}
