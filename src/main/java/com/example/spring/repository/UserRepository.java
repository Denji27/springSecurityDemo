package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByEmail(String email);

}
