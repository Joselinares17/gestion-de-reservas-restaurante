package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private int quantityPersons;
    private LocalDate date;
    private LocalTime time;
    private boolean isActive;
    private Customer customer;

    public Reservation(int quantityPersons, LocalDate date, LocalTime time, Customer customer) {
        this.id = UUID.randomUUID();
        setQuantityPersons(quantityPersons);
        setDate(date);
        setTime(time);
        setCustomer(customer);
        this.isActive = true;
    }

    public UUID getId() {
        return id;
    }

    public int getQuantityPersons() {
        return quantityPersons;
    }

    public void setQuantityPersons(int quantityPersons) {
        if(quantityPersons < 0 || quantityPersons > 8) throw new IllegalArgumentException("La cantidad de personas debe estar entre 0 y 8.");
        this.quantityPersons = quantityPersons;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if(date.isBefore(LocalDate.now())) throw new IllegalArgumentException("No se puede reservar una fecha pasada.");
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        if(time.isBefore(LocalTime.parse("12:00")) || time.isAfter(LocalTime.parse("23:00"))) throw new IllegalArgumentException("Fuera del horario de atención.");
        this.time = time;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if(customer == null) throw new RuntimeException("El cliente no puede ser nulo.");
        this.customer = customer;
    }

    @Override
    public String toString() {
        // Formateadores para una salida consistente
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Determina el estado de la reserva en texto
        String estado = this.isActive ? "Activa" : "Cancelada";

        // Construye la cadena de texto con el formato deseado
        return "Detalles de la Reserva:\n" +
                "  - Id: " + this.id + "\n" +
                "  - Cliente: " + this.customer.getName() + " " + this.customer.getLastName() + "\n" +
                "  - Fecha: " + this.date.format(dateFormatter) + "\n" +
                "  - Hora: " + this.time.format(timeFormatter) + "\n" +
                "  - Personas: " + this.quantityPersons + "\n" +
                "  - Estado: " + estado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(date, that.date) && Objects.equals(time, that.time) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, customer);
    }
}
