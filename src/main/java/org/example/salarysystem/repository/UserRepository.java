package org.example.salarysystem.repository;

import org.example.salarysystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Required by Spring Security to load user details during login
    Optional<User> findByUsername(String username);

    // Check duplication during registration (if implemented)
    boolean existsByUsername(String username);
}