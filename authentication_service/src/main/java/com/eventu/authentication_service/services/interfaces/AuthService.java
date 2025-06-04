package com.eventu.authentication_service.services.interfaces;

import com.eventu.authentication_service.services.request.LoginRequest;
import com.eventu.authentication_service.services.request.RegisterRequest;
import com.eventu.authentication_service.services.response.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public AuthResponse registerBaseUser(RegisterRequest registerRequest) throws Exception;

    public AuthResponse loginUser(LoginRequest loginRequest);
}
