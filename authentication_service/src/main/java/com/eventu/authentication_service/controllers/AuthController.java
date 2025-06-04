package com.eventu.authentication_service.controllers;

import com.eventu.authentication_service.services.interfaces.AuthService;
import com.eventu.authentication_service.services.request.LoginRequest;
import com.eventu.authentication_service.services.request.RegisterRequest;
import com.eventu.authentication_service.services.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> createUserHandler(
            @RequestBody RegisterRequest registerRequest) throws Exception{
        AuthResponse response = authService.registerBaseUser(registerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse>logIn
            (@RequestBody LoginRequest loginRequest){
        AuthResponse authResponse = authService.loginUser(loginRequest);
        return  new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}
