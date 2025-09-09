package com.nikita.restaurantservice.repository;

import com.nikita.restaurantservice.model.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity,UUID> {
    boolean existsByName(String name);
    Optional<RestaurantEntity> findByName(String name);

    @EntityGraph(attributePaths = "menu")
    Optional<RestaurantEntity> findWithMenuById(UUID id);

}
