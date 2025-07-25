# Sistema de Gestión de Reservas para Restaurante

Este es un proyecto simple de una aplicación de consola desarrollada en Java para gestionar las reservas de un restaurante. La aplicación permite realizar operaciones básicas de creación, consulta y cancelación de reservas, siguiendo una arquitectura por capas para una clara separación de responsabilidades.

## Características Principales

* **Registrar Nuevas Reservas:** Permite crear reservas para clientes, especificando el número de personas, fecha y hora.
* **Listar Reservas Activas:** Muestra un listado de todas las reservas que no han sido canceladas.
* **Buscar por Cliente:** Permite buscar todas las reservas asociadas a un nombre de cliente específico.
* **Cancelar Reservas:** Ofrece la funcionalidad de cancelar una reserva existente a través de su ID único.
* **Validación de Reglas de Negocio:**
    * No permite reservas para más de 8 personas.
    * El horario de atención está restringido (12:00 a 23:00).
    * Impide la creación de reservas en fechas pasadas.
    * Evita el registro de reservas duplicadas (mismo cliente, misma fecha y hora).
* **Manejo de Errores:** Proporciona feedback claro al usuario cuando los datos ingresados son inválidos o cuando se viola una regla de negocio.

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes para mantener una arquitectura limpia:

* `main`: Contiene la clase `App` que es el punto de entrada de la aplicación.
* `entity`: Define las clases del modelo de datos (`Customer`, `Reservation`).
* `repository`: Simula la capa de persistencia para el almacenamiento de datos en memoria (`ReservationRepository`).
* `service`: Contiene la lógica de negocio, validaciones y operaciones (`ReservationService`). Incluye un subpaquete `service.exception` para excepciones personalizadas.
* `view`: Se encarga de toda la interacción con el usuario a través de la consola (`TerminalView`).

