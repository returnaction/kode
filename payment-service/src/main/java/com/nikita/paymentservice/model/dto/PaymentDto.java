package com.nikita.paymentservice.model.dto;

import com.nikita.paymentservice.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private UUID paymentId;
    private UUID userId;
    private UUID orderId;
    private Double amount;
    private Status status;
}
