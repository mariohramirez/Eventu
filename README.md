# Eventu

El aplicativo es un sistema multiplataforma que permite la gestión, agendamiento y realización de todos los procesos necesarios para la compra de boletería en cuanto a distintos eventos culturales, educativos, sociales y de ocio en las distintas ciudades. Ha sido planeado con una arquitectura de microservicios, buscando así una mayor modularidad y escalabilidad del proyecto a medida que el mismo vaya creciendo, tanto en su propio alcance como en la afluencia de usuarios.

### Objetivo General:

Desarrollar un aplicativo multiplataforma, escalable y modular, dedicado a abordar los procesos de gestión, agendamiento y transaccionalidad de eventos culturales, educativos, sociales y de ocio en las ciudades.

### Objetivos específicos:

#### 1. Gestión de usuarios y autenticación:

- Implementar un servicio de autenticación seguro con JWT.

- Definir distintos roles para los usuarios que usen las distintas funcionalidades del aplicativo desde administradores, organizadores hasta asistentes.

- Gestionar perfiles, permisos y roles.
Permitir el registro y acceso a la aplicación mediante la conexión por email y redes sociales.

#### 2. Gestión de eventos:

- Crear un CRUD completo de eventos en el cual se indiquen los detalles del evento como el nombre, descripción, fecha, lugar, costo, organizador u organizadores, categoría, etc.

- Actualizar la publicación del evento en tiempo real, teniendo en cuenta etapas de compra de boletas, las fechas del mismo y su disponibilidad

- Organizar a manera de agenda para los asistentes, los distintos eventos a los que se hayan registrado o que hayan pagado

#### 3. Pasarela de pagos y procesamiento de eventos:

- Implementar un servicio que procese los pagos para eventos que tengan costo

- Manejar eventos al momento de pago, como es el proceso del mismo, pago realizado, pago cancelado

- Manejar cancelaciones y reembolsos

## Modelos ER:

### Microservicio autenticación:

![Diagrama ER (1)](https://github.com/mariohramirez/Eventu/blob/4293b677d9659086f40c8baf33d59cc815d800c8/Modelos%20ER/auth_service_db.png)

### Microservicio eventos:

![Diagrama ER (1)](https://github.com/mariohramirez/Eventu/blob/4293b677d9659086f40c8baf33d59cc815d800c8/Modelos%20ER/SqlEventoMicroservicio.png)
