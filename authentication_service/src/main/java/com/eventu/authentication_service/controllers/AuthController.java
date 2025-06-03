package com.eventu.authentication_service.controllers;

import com.eventu.authentication_service.config.JwtProvider;
import com.eventu.authentication_service.controllers.request.LoginRequest;
import com.eventu.authentication_service.controllers.response.AuthResponse;
import com.eventu.authentication_service.model.gateway.RoleRepository;
import com.eventu.authentication_service.model.gateway.UserRepository;
import com.eventu.authentication_service.services.CustomUserDetailsService;
import com.eventu.authentication_service.services.interfaces.AuthService;
import com.eventu.authentication_service.services.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> createUserHandler(
            @RequestBody RegisterRequest registerRequest) throws Exception{
        AuthResponse response = authService.registerBaseUser(registerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse>signIn(@RequestBody LoginRequest req){

        String username = req.getEmail();
        String password = req.getPassword();

        System.out.println(username + "------" + password);

        Authentication authentication=authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login exitoso");
        authResponse.setStatus(true);

        return  new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        System.out.println("Detalles de inicio de sesion: "+userDetails);

        if(userDetails==null){
            throw new BadCredentialsException("Nombre de usuario invalido...");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Contrasena invalida...");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
