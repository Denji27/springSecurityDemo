package com.example.spring.controller;

import com.example.spring.entity.User;
import com.example.spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
@AllArgsConstructor
public class UserController {
	private UserService userService;
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
		return ResponseEntity.ok(userService.register(registerRequest));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
		return ResponseEntity.ok(userService.authenticate(authenticationRequest));
	}

	@PostMapping("/create-admin")
	public ResponseEntity<?> createAdmin(@RequestBody RegisterRequest registerRequest){
		return ResponseEntity.ok(userService.createAdmin(registerRequest));
	}

}
