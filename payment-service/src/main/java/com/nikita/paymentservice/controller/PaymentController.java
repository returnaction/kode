package com.nikita.paymentservice.controller;

import com.nikita.paymentservice.model.Status;
import com.nikita.paymentservice.model.dto.PaymentDto;
import com.nikita.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto request){
        return paymentService.createPayment(request);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable UUID paymentId){
        return paymentService.getPayment(paymentId);
    }

    @PutMapping("/{paymentId}/user/{userId}")
    public ResponseEntity<PaymentDto> updatePaymentStatus(@PathVariable UUID paymentId, @PathVariable UUID userId, @RequestParam Status status){
        return paymentService.updatePaymentStatus(paymentId, userId, status);
    }
}
