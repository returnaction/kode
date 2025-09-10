package com.nikita.deliveryservice.repository;

import com.nikita.deliveryservice.model.entity.delivery.DeliveryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DeliveryRepository extends CrudRepository<DeliveryEntity, UUID> {
}
