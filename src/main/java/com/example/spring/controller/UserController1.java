package com.example.spring.controller;

import com.example.spring.entity.User;
import com.example.spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController1 {

    private UserService userService;
    @GetMapping("/all-users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> showAllUsers(){
        return ResponseEntity.ok(userService.showAllUser());
    }

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/assign-as-user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> assignAsUser(@RequestBody Email email){
        return ResponseEntity.ok(userService.assignAsUser(email.getEmail()));
    }

}
