package com.nikita.deliveryservice.repository;

import com.nikita.deliveryservice.model.entity.courier.CourierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourierRepository extends JpaRepository<CourierEntity, UUID> {
}
