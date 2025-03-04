package com.foodhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodhub.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndIsVerified(String email, boolean isVerified);
    Optional<User> findByVerificationToken(String token);
}
