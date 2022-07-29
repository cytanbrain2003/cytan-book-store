package com.example.cytanbookstore.controller;

import com.example.cytanbookstore.entities.Role;
import com.example.cytanbookstore.entities.User;
import com.example.cytanbookstore.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoginController {

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestBody User user){
        if (userService.findByUsername(user.getUsername()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        user.setRole(Role.USER);
        System.out.println("User : "+user);
        return new ResponseEntity<>(userService.register(user),HttpStatus.CREATED);
    }
    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        var loginedInUser = userService.findByUsername(user.getUsername());
        if (loginedInUser != null) {
            if (passwordEncoder.matches(user.getPassword(), loginedInUser.getPassword())) {
                System.out.println("Password Match");
                return new ResponseEntity<>(loginedInUser, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

    }

}
