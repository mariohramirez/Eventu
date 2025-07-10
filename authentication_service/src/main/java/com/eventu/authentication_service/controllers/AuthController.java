package com.eventu.authentication_service.controllers;

import com.eventu.authentication_service.services.interfaces.AuthService;
import com.eventu.authentication_service.services.request.LoginRequest;
import com.eventu.authentication_service.services.request.RegisterRequest;
import com.eventu.authentication_service.services.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> createUserHandler(@RequestBody RegisterRequest registerRequest) {
        try {
            AuthResponse response = authService.registerBaseUser(registerRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // Devuelve un objeto JSON en lugar de un String plano
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", "400");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse>logIn
            (@RequestBody LoginRequest loginRequest){
        AuthResponse authResponse = authService.loginUser(loginRequest);
        return  new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}
