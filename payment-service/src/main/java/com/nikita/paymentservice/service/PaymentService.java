package com.nikita.paymentservice.service;

import com.nikita.paymentservice.mapper.PaymentMapper;
import com.nikita.paymentservice.model.dto.PaymentDto;
import com.nikita.paymentservice.model.entity.PaymentEntity;
import com.nikita.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public ResponseEntity<PaymentDto> createPayment(PaymentDto request) {
        PaymentEntity entity = PaymentMapper.toEntity(request);
        entity = paymentRepository.save(entity);
        PaymentDto dto = PaymentMapper.toDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PaymentDto> getPayment(UUID paymentId) {
        PaymentEntity payment = paymentRepository.findById(paymentId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Платеж не найден"));
        PaymentDto dto = PaymentMapper.toDto(payment);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
