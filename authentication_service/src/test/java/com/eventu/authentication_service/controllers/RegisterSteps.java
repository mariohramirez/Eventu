package com.eventu.authentication_service.controllers;

import com.eventu.authentication_service.model.*;
import com.eventu.authentication_service.model.gateway.*;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.CucumberOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@CucumberOptions(features = "src/test/resources/features/register.feature")
public class RegisterSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ResponseEntity<Map<String, Object>> response;
    private List<Long> createdUserIds = new ArrayList<>();
    private List<Integer> createdRoleIds = new ArrayList<>();

    @After // Se ejecuta después de cada escenario
    public void cleanTestUsers() {
        userRepository.deleteAllById(createdUserIds);
        createdUserIds.clear();
    }

    // Background steps
    @Given("Existe el siguiente rol en el sistema:")
    public void existe_rol_en_sistema(io.cucumber.datatable.DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String roleName = row.get("name");
            RoleName roleNameEnum = RoleName.valueOf(roleName);

            // Buscar el rol existente
            Role existingRole = roleRepository.findByName(roleNameEnum);

            // Si no existe, crearlo
            if (existingRole == null) {
                Role newRole = new Role();
                newRole.setName(roleNameEnum);
                existingRole = roleRepository.save(newRole);
            }

            createdRoleIds.add(existingRole.getId());
        });
    }

    @Given("No existe ningún usuario registrado con email {string}")
    public void no_existe_usuario_con_email(String email) {
        User user = userRepository.findByEmail(email);
        assertNull(user, "El usuario con email " + email + " ya existe");
    }

    @Given("Existe un usuario registrado con email {string}")
    public void existe_usuario_registrado_con_email(String email) {
        // Crear usuario pre-existente para el test
        User existingUser = new User();
        existingUser.setEmail(email);
        existingUser.setPassword(passwordEncoder.encode("TempPass123"));
        existingUser.setActive(true);

        userRepository.save(existingUser);
        createdUserIds.add(existingUser.getId()); // Para limpieza posterior
    }

    // Registro exitoso steps
    @When("Envío una solicitud POST a {string} con:")
    public void envio_solicitud_registro(String endpoint, String requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        response = restTemplate.exchange(
                endpoint,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );
    }

    @Then("Recibo una respuesta HTTP {int}")
    public void recibo_respuesta_http(int statusCode) {
        assertEquals(statusCode, response.getStatusCodeValue());
    }

    @And("La respuesta contiene un JWT válido")
    public void respuesta_contiene_jwt() {
        assertNotNull(response.getBody().get("jwt"));
        String jwt = (String) response.getBody().get("jwt");
        assertTrue(jwt.startsWith("eyJ") && jwt.split("\\.").length == 3);
    }

    @And("La respuesta contiene el mensaje {string}")
    public void respuesta_contiene_mensaje(String mensajeEsperado) {
        // Versión segura que maneja diferentes estructuras de respuesta
        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody, "El cuerpo de la respuesta está vacío");

        // Busca el mensaje en diferentes posibles campos
        String mensajeActual = Optional.ofNullable(responseBody.get("message"))
                .orElse(responseBody.get("error"))
                .toString();
        assertTrue(mensajeActual.contains(mensajeEsperado),
                "El mensaje esperado: '" + mensajeEsperado +
                        "' no se encontró en: '" + mensajeActual + "'");
    }

    @And("El usuario queda registrado en el sistema con email {string}")
    public void usuario_registrado_con_email(String email) {
        User user = userRepository.findByEmail(email);
        assertNotNull(user, "Usuario no encontrado con email: " + email);
        createdUserIds.add(user.getId());

        // Verificar password fue hasheado
        assertTrue(passwordEncoder.matches("SecurePass123", user.getPassword()));
    }


}