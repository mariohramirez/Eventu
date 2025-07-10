Feature: Creación de eventos
  Como organizador de eventos
  Quiero poder crear nuevos eventos
  Para poder gestionar mis actividades públicas

  Scenario: Creación exitosa de evento con todos los campos
    When Envío una solicitud POST a "/api/events/create" con:
      """
      {
        "title": "Festival de Música Independiente 2024",
        "description": "El mejor festival de música independiente de Medellín con artistas locales e internacionales",
        "shortDescription": "Festival musical anual",
        "startTime": "2024-11-15T18:00:00",
        "endTime": "2024-11-17T23:00:00",
        "organizerId": 1,
        "status": "PUBLISHED",
        "visibility": "PUBLIC",
        "maxAttendees": 5000,
        "minAttendees": 100,
        "currentAttendees": 0,
        "publishedAt": "2024-09-01T00:00:00",
        "locationRequest": {
          "name": "Parque Explora",
          "description": "Museo interactivo de ciencia y tecnología",
          "addressLine1": "Carrera 52 # 73-75",
          "city": "Medellín",
          "state": "Antioquia",
          "country": "Colombia",
          "latitude": 6.2705,
          "longitude": -75.5649,
          "isOnline": false,
          "timezone": "America/Bogota",
          "capacity": 2000,
          "facilities": ["acuario", "planetario", "zona_interactiva"]
        },
        "categoryRequest": [
          {"id": 3}
        ],
        "tagRequest": [
          {"name": "Indies", "color": "1DB954"},
          {"name": "Festivos", "color": "FF5722"}
        ]
      }
      """
    Then Recibo una respuesta HTTP 201
    And La respuesta contiene los datos del evento creado
    And El evento queda registrado en el sistema con título "Festival de Música Independiente 2024"

  Scenario: Creación fallida - campos requeridos faltantes
    When Envío una solicitud POST a "/api/events/create" con:
      """
      {
        "title": "",
        "startTime": "",
        "endTime": "",
        "organizerId": null,
        "locationRequest": null
      }
      """
    Then Recibo una respuesta HTTP 400
    And La respuesta contiene el mensaje "Los campos título, fechas y ubicación son obligatorios"
