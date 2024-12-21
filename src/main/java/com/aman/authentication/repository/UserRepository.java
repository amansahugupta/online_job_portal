package com.aman.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aman.authentication.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
