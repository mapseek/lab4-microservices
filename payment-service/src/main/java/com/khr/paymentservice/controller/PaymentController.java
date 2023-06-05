package com.khr.paymentservice.controller;

import com.khr.paymentservice.dto.PaymentDto;
import com.khr.paymentservice.dto.PaymentRequest;
import com.khr.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentService service;

    @GetMapping
    public List<PaymentDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/clients/{clientId}")
    public List<PaymentDto> getAllByClientId(@PathVariable Long clientId){
        return service.getAllByClientId(clientId);
    }


    @GetMapping("/accounts/{accountId}")
    public List<PaymentDto> getAllByAccountId(@PathVariable Long accountId){
        return service.getAllByAccountId(accountId);
    }

    @GetMapping("/{id}")
    public PaymentDto getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public PaymentDto makePayment(@RequestBody PaymentRequest paymentRequest) {
        return service.makePayment(paymentRequest.getAccountId(), paymentRequest.getAmount());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id){
        service.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
