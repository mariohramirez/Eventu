package com.eventu.authentication_service.controllers.response;

import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;

    private String message;

    private Boolean status;
}
