package com.maria.gym.controller;

import com.maria.gym.model.Credential;
import com.maria.gym.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> login(@RequestBody @Valid Credential credential){
        return null;
    }
}
