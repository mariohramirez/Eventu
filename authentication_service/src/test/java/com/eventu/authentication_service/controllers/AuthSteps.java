package com.eventu.authentication_service.controllers;

import com.eventu.authentication_service.model.User;
import com.eventu.authentication_service.model.gateway.UserRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@CucumberOptions(features = "src/test/resources/features/login.feature")
public class AuthSteps {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private ResponseEntity<Map> response;

    private HttpHeaders headers = new HttpHeaders();

    private List<Long> createdUserIds = new ArrayList<>();


    @After // Se ejecuta después de cada escenario
    public void cleanTestUsers() {
        userRepository.deleteAllById(createdUserIds);
        createdUserIds.clear();
    }

    @Given("Existen los siguientes usuarios en el sistema:")
    public void setUpUsers(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
        User user = new User();
        user.setEmail(row.get("email"));
        user.setPassword(passwordEncoder.encode("SecurePass123"));
        user.setActive(Boolean.parseBoolean(row.get("is_active")));
        userRepository.save(user);
        createdUserIds.add(user.getId());
        });
    }
    @When("Envío una solicitud a {string} con:")
    public void sendLoginRequest(String endpoint, String requestBody) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.remove("Authorization");
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        response = restTemplate.postForEntity(
                endpoint,
                request,
                Map.class
        );
    }

    @Then("Recibo una respuesta HTTP {int} de autenticación")
    public void verifyStatusCode(int expectedStatus) {
        assertEquals(expectedStatus, response.getStatusCodeValue());
    }

    @And("La respuesta contiene un JWT válido de autenticación")
    public void verifyJwtInResponse() {
        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody.get("jwt"));
        assertTrue(((String) responseBody.get("jwt")).startsWith("eyJ"));
    }

    @When("Envío una solicitud POST de autenticación a {string} con:")
    public void envioSolicitudLogin(String endpoint, String requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        headers.remove("Authorization");
        try {
            response = restTemplate.postForEntity(
                    endpoint,
                    request,
                    Map.class
            );
        } catch (HttpClientErrorException e) {
            System.out.println("Error");
        }
    }




}
