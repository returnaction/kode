package com.nikita.deliveryservice.model.entity.courier;

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
@Table(name = "couriers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourierEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID courierId;
    private String name;
    private String email;
    private String phone;

}
