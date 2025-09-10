package com.nikita.paymentservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID paymentId;

    //TODO добавить валидацию
    //TODO добавить createdtAt updatedAt
    @Column(nullable = false)
    private UUID userId;
    @Column(nullable = false)
    private UUID orderId;
    @Column(nullable = false)
    private Double amount;
}
