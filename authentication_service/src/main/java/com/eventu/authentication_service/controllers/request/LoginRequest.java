package com.eventu.authentication_service.controllers.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String password;
}
