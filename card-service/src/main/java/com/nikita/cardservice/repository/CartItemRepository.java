package com.nikita.cardservice.repository;

import com.nikita.cardservice.model.entity.CartEntity;
import com.nikita.cardservice.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {
    List<CartItemEntity> findAllByCartId (UUID cartId);
}
