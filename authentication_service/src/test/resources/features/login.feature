Feature: Autenticación de usuario
  Como usuario del sistema quiero iniciar sesión con mis credenciales
  Para acceder a las funcionalidades protegidas

  Background:
    Given Existen los siguientes usuarios en el sistema:
      | email            | password_hash | is_active |
      | user@example.com | SecurePass123 | true      |
      | blocked@test.com | SecurePass123 | false     |

  Scenario: Login exitoso con credenciales válidas
    When Envío una solicitud a "/api/auth/login" con:
      """
      {
        "email": "user@example.com",
        "password": "SecurePass123"
      }
      """
    Then Recibo una respuesta HTTP 200 de autenticación
    And La respuesta contiene un JWT válido de autenticación

  Scenario: Login fallido - contraseña incorrecta
    When Envío una solicitud POST de autenticación a "/api/auth/login" con:
    """
    {
      "email": "user@example.com",
      "password": "WrongPassword"
    }
    """
    Then Recibo una respuesta HTTP 403 de autenticación

  Scenario: Login fallido - usuario no existe
    When Envío una solicitud POST de autenticación a "/api/auth/login" con:
    """
    {
      "email": "unknown@user.com",
      "password": "anypassword"
    }
    """
    Then Recibo una respuesta HTTP 403 de autenticación