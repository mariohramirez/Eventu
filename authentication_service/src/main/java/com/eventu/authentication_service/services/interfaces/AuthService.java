package com.eventu.authentication_service.services.interfaces;

import com.eventu.authentication_service.controllers.response.AuthResponse;
import com.eventu.authentication_service.services.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public AuthResponse registerBaseUser(RegisterRequest registerRequest) throws Exception;
}
