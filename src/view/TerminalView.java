package view;

import entity.Customer;
import entity.Reservation;
import service.exception.DuplicateReservationException;
import service.IReservationService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class TerminalView {
    private final IReservationService reservationService;
    private final Scanner scanner;

    public TerminalView(IReservationService reservationService) {
        this.reservationService = reservationService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        generateSampleData();
        boolean exit = false;
        while (!exit) {
            displayMenu();
            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1: registerNewReservation(); break;
                    case 2: listActiveReservations(); break;
                    case 3: findReservationsByCustomer(); break;
                    case 4: cancelReservation(); break;
                    case 5:
                        exit = true;
                        System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
                        break;
                    default: System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            }
            if (!exit) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n--- SISTEMA DE RESERVAS ---");
        System.out.println("1. Registrar nueva reserva");
        System.out.println("2. Listar reservas activas");
        System.out.println("3. Buscar reservas por cliente");
        System.out.println("4. Cancelar reserva");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void registerNewReservation() {
        try {
            System.out.println("\n--- Registro de Nueva Reserva ---");
            System.out.print("Nombre del cliente: ");
            String name = scanner.nextLine();
            System.out.print("Apellido del cliente: ");
            String lastName = scanner.nextLine();
            System.out.print("Email del cliente: ");
            String email = scanner.nextLine();
            System.out.print("Teléfono del cliente (9 dígitos): ");
            String phone = scanner.nextLine();
            Customer customer = new Customer(name, lastName, email, phone);

            System.out.print("Número de personas (1-8): ");
            int personCount = Integer.parseInt(scanner.nextLine());
            System.out.print("Fecha de la reserva (dd/MM/yyyy): ");
            LocalDate date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.print("Hora de la reserva (HH:mm - formato 24h): ");
            LocalTime time = LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));

            Reservation reservation = new Reservation(personCount, date, time, customer);
            reservationService.saveReservation(reservation);
            System.out.println("\n¡Reserva registrada con éxito!");
            System.out.println(reservation);
        } catch (DuplicateReservationException e) { // Bloque específico para el error de duplicado
            System.out.println("\nError al registrar: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("\nError en los datos: El formato de fecha u hora es incorrecto.");
        } catch (NumberFormatException e) {
            System.out.println("\nError en los datos: El número de personas debe ser un valor numérico.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nError en los datos: " + e.getMessage());
        } catch (Exception e) { // Captura cualquier otro error inesperado
            System.out.println("\nHa ocurrido un error inesperado: " + e.getMessage());
        }
    }

    private void listActiveReservations() {
        System.out.println("\n--- Listado de Reservas Activas ---");
        List<Reservation> reservations = reservationService.getActiveReservations();
        if (reservations.isEmpty()) {
            System.out.println("No hay reservas activas en este momento.");
        } else {
            reservations.forEach(r -> System.out.println(r.toString() + "\n--------------------"));
        }
    }

    private void findReservationsByCustomer() {
        System.out.println("\n--- Búsqueda de Reservas por Cliente ---");
        System.out.print("Ingrese el nombre del cliente a buscar: ");
        String name = scanner.nextLine();
        List<Reservation> reservations = reservationService.getReservationsByCustomerName(name);

        if (reservations.isEmpty()) {
            System.out.println("No se encontraron reservas para el cliente '" + name + "'.");
        } else {
            System.out.println("Reservas encontradas para '" + name + "':");
            reservations.forEach(System.out::println);
        }
    }

    private void cancelReservation() {
        System.out.println("\n--- Cancelación de Reserva ---");
        System.out.print("Ingrese el nombre del cliente de la reserva a cancelar: ");
        String name = scanner.nextLine();
        try {
            reservationService.cancelReservationByCustomerName(name);
            System.out.println("La reserva más reciente para el cliente '" + name + "' ha sido cancelada.");
        } catch (RuntimeException e) {
            System.out.println("Error al cancelar: " + e.getMessage());
        }
    }

    private void generateSampleData() {
        System.out.println("Cargando datos de ejemplo...");
        try {
            Customer c1 = new Customer("Carlos", "Santana", "csantana@music.com", "987654321");
            Reservation r1 = new Reservation(4, LocalDate.of(2025, 8, 15), LocalTime.of(20, 0), c1);
            reservationService.saveReservation(r1);

            Customer c2 = new Customer("Deborah", "Harry", "dharry@blondie.com", "912345678");
            Reservation r2 = new Reservation(2, LocalDate.of(2025, 8, 15), LocalTime.of(21, 30), c2);
            reservationService.saveReservation(r2);

            Customer c3 = new Customer("Jhonny", "Cash", "jcash@black.com", "999888777");
            Reservation r3 = new Reservation(5, LocalDate.of(2025, 8, 16), LocalTime.of(19, 0), c3);
            reservationService.saveReservation(r3);

            Customer c4 = new Customer("Nina", "Simone", "nsimone@jazz.com", "965432109");
            Reservation r4 = new Reservation(3, LocalDate.of(2025, 8, 17), LocalTime.of(13, 0), c4);
            reservationService.saveReservation(r4);

            Reservation r5 = new Reservation(6, LocalDate.of(2025, 8, 20), LocalTime.of(20, 30), c1);
            reservationService.saveReservation(r5);

            System.out.println("Datos de ejemplo cargados con éxito.");
        } catch (Exception e) {
            System.out.println("Error al cargar los datos de ejemplo: " + e.getMessage());
        }
    }
}
