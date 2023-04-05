package com.example.spring;

import com.example.spring.entity.Role;
import com.example.spring.entity.User;
import com.example.spring.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication{

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);

	}
}
