package com.eventu.authentication_service.services.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private String avatar;
}
