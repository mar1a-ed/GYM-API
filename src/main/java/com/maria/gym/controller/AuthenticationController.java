package com.maria.gym.controller;

import com.maria.gym.dto.UserLoginDTO;
import com.maria.gym.jwt.JwtService;
import com.maria.gym.jwt.MyUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/public/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final MyUserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtService jwtService, MyUserDetailsService userDetailsService){
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid UserLoginDTO loginDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getEmail());

        String token = jwtService.getToken(userDetails);

        return ResponseEntity.ok(Map.of("token", token));
    }
}
