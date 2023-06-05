package com.khr.accountservice.controller;

import com.khr.accountservice.dto.AccountDto;
import com.khr.accountservice.dto.PaymentDto;
import com.khr.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService service;

    @GetMapping
    public List<AccountDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/clients/{clientId}")
    public List<AccountDto> getAllByClient(@PathVariable Long clientId){
        return service.getAllByClientId(clientId);
    }

    @GetMapping("/{id}")
    public AccountDto getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/{id}/payments")
    public List<PaymentDto> getAllPayments(@PathVariable Long id){
        return service.getAllByAccountId(id);
    }

    @PostMapping
    public AccountDto createAccount(@RequestBody AccountDto createBody){
        return service.createAccount(createBody);
    }

    @PatchMapping("/{id}")
    public AccountDto updateAccount(@PathVariable Long id,@RequestBody AccountDto updateBody){
        return service.updateAccount(id,updateBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        service.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
