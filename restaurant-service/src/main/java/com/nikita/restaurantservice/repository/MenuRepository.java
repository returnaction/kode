package com.nikita.restaurantservice.repository;

import com.nikita.restaurantservice.model.dto.MenuDto;
import com.nikita.restaurantservice.model.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuRepository extends JpaRepository<MenuEntity, UUID> {
    boolean existsByRestaurant_IdAndName(UUID restaurantId, String name);
}

