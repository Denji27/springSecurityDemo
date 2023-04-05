package com.example.spring.service.impl;

import com.example.spring.controller.AuthenticationRequest;
import com.example.spring.controller.AuthenticationResponse;
import com.example.spring.controller.RegisterRequest;
import com.example.spring.entity.Role;
import com.example.spring.entity.User;
import com.example.spring.repository.UserRepository;
import com.example.spring.service.JwtService;
import com.example.spring.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .userName(registerRequest.getUserName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.NONE)
                .build();

        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwt(jwt)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()
            )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwt(jwt)
                .build();
    }

    @Override
    public List<User> showAllUser() {
        userRepository.findAll();
        return userRepository.findAll();
    }


    @Override
    public AuthenticationResponse createAdmin(RegisterRequest registerRequest) {
        User user = User.builder()
                .userName(registerRequest.getUserName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwt(jwt)
                .build();
    }


}
