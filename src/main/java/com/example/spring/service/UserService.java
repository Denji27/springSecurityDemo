package com.example.spring.service;

import com.example.spring.controller.AssignRequest;
import com.example.spring.controller.AuthenticationRequest;
import com.example.spring.controller.AuthenticationResponse;
import com.example.spring.controller.RegisterRequest;
import com.example.spring.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

public interface UserService {
    public AuthenticationResponse register(RegisterRequest registerRequest);
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    public List<User> showAllUser();
    public void assignAsUser(AssignRequest assignRequest);
    public AuthenticationResponse createAdmin(RegisterRequest registerRequest);
    public void refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException;
}
