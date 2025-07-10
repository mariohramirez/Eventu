Feature: Registro de usuario
  Como nuevo usuario del sistema
  Quiero registrarme con mis datos personales
  Para poder acceder a las funcionalidades de la plataforma

  Background:
    Given Existe el siguiente rol en el sistema:
      | name  |
      | USER  |
    And No existe ningún usuario registrado con email "usergherkin@example.com"

  Scenario: Registro exitoso con datos válidos
    When Envío una solicitud POST a "/api/auth/register" con:
      """
      {
        "email": "usergherkin@example.com",
        "password": "SecurePass123",
        "firstName": "John",
        "lastName": "Doe",
        "phone": "+1234567890",
        "avatar": "https://example.com/avatar.jpg"
      }
      """
    Then Recibo una respuesta HTTP 201
    And La respuesta contiene un JWT válido
    And La respuesta contiene el mensaje "Registro exitoso"
    And El usuario queda registrado en el sistema con email "usergherkin@example.com"

  Scenario: Registro fallido - email ya existe
    Given Existe un usuario registrado con email "existing@user.com"
    When Envío una solicitud POST a "/api/auth/register" con:
      """
      {
        "email": "existing@user.com",
        "password": "SecurePass123",
        "firstName": "John",
        "lastName": "Doe",
        "phone": "+1234567890",
        "avatar": "https://example.com/avatar.jpg"
      }
      """
    Then Recibo una respuesta HTTP 400
    And La respuesta contiene el mensaje "El email ya existe"

  Scenario: Registro fallido - campos requeridos faltantes
    When Envío una solicitud POST a "/api/auth/register" con:
      """
      {
        "email": "",
        "password": "",
        "firstName": "",
        "lastName": ""
      }
      """
    Then Recibo una respuesta HTTP 400