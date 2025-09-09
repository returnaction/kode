package com.nikita.cardservice.repository;

import com.nikita.cardservice.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<CartRepository, UUID> {
    Optional<CartEntity> findByUserId(UUID userId);
    boolean existsByUserId(UUID userId);
    void deleteByUserId(UUID userId);
}
