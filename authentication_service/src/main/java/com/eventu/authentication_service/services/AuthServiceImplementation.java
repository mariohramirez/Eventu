package com.eventu.authentication_service.services;

import com.eventu.authentication_service.config.JwtProvider;
import com.eventu.authentication_service.controllers.response.AuthResponse;
import com.eventu.authentication_service.model.*;
import com.eventu.authentication_service.model.gateway.RoleRepository;
import com.eventu.authentication_service.model.gateway.UserProfileRepository;
import com.eventu.authentication_service.model.gateway.UserRepository;
import com.eventu.authentication_service.model.gateway.UserRoleRepository;
import com.eventu.authentication_service.services.interfaces.AuthService;
import com.eventu.authentication_service.services.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public AuthResponse registerBaseUser
            (RegisterRequest registerRequest) throws Exception {
        if(userRepository.findByEmail(registerRequest.getEmail())!=null){
            throw new Exception("El email ya existe");
        }

        //Crea al usuario
        User userCreated = new User();
        userCreated.setEmail(registerRequest.getEmail());
        userCreated.setUserNumber(UUID.randomUUID().toString());
        userCreated.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userCreated.setOrganization(null);
        userCreated.setCreatedAt(LocalDateTime.now());
        userCreated.setUpdatedAt(LocalDateTime.now());
        userCreated.setActive(true);

        //Crea el perfil del usuario
        UserProfile profileCreated = new UserProfile();
        profileCreated.setFirstName(registerRequest.getFirstName());
        profileCreated.setLastName(registerRequest.getLastName());
        profileCreated.setPhone(registerRequest.getPhone());
        profileCreated.setAvatarUrl(registerRequest.getAvatar());
        profileCreated.setUser(userCreated);

        userProfileRepository.save(profileCreated);

        userCreated.setProfile(profileCreated);

        assignRole(userCreated, RoleName.USER, null);

        User savedUser = userRepository.save(userCreated);

        Authentication authentication = new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registro exitoso");

        return authResponse;
    }

    private void assignRole(User user, RoleName roleName, Long organizationId) {
        Role role = roleRepository.findByName(roleName);
        System.out.println("Este es el rol encontrado"+role);

        UserRoleId userRoleId = new UserRoleId();
        userRoleId.setUserId(user.getId());
        userRoleId.setRoleId(role.getId());

        UserRole userRole = new UserRole();
        userRole.setId(userRoleId);
        userRole.setUser(user);
        userRole.setRole(role);

        if(organizationId!=null){
            userRole.setOrganization(user.getOrganization());
        }

        userRoleRepository.save(userRole);

    }
}
