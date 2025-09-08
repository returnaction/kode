package com.nikita.userservice.repository;

import com.nikita.userservice.model.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByPhone(String phone);

    Optional<UserEntity> findByPhone(String phone);

    //TODO потом может переписать все на join fetch пока оставлю так
    @EntityGraph(attributePaths = "addresses")
    Optional<UserEntity> findWithAddressesById(UUID id);
}
