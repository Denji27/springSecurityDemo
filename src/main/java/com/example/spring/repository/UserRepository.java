package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByEmail(String email);

    @Query(value = "select u from User u where u.email = :email")
    public List<User> findUserByEmail(String email);
}
