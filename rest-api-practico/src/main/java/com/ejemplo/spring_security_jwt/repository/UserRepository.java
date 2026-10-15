package com.ejemplo.spring_security_jwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ejemplo.spring_security_jwt.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  boolean existsByUsername(String username);
  boolean existsByEmail(String username);
  @Query("SELECT u FROM User u ORDER BY u.id")
  List<User> findAllUsers();
}
