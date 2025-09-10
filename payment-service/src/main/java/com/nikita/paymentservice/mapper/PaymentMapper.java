package com.nikita.paymentservice.mapper;

import com.nikita.paymentservice.model.dto.PaymentDto;
import com.nikita.paymentservice.model.entity.PaymentEntity;

public class PaymentMapper {
    public static PaymentEntity toEntity(PaymentDto dto) {
        return PaymentEntity.builder()
                .userId(dto.getUserId())
                .orderId(dto.getOrderId())
                .amount(dto.getAmount())
                .build();
    }

    public static PaymentDto toDto(PaymentEntity entity) {
        return PaymentDto.builder()
                .paymentId(entity.getPaymentId())
                .userId(entity.getUserId())
                .orderId(entity.getOrderId())
                .amount(entity.getAmount())
                .build();
    }
}
