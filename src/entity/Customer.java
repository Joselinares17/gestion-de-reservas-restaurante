package entity;

import java.util.Objects;

public class Customer {
    private String name;
    private String lastName;
    private String email;
    private String phone;

    public Customer(String name, String lastName, String email, String phone) {
        setName(name);
        setLastName(lastName);
        setEmail(email);
        setPhone(phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío.");
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName == null || lastName.isBlank()) throw new IllegalArgumentException("El apellido no puede estar vacío.");
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || email.isBlank()) throw new IllegalArgumentException("El apellido no puede estar vacío.");
        if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) throw new IllegalArgumentException("El email no tiene formato de correo electrónico.");
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(phone == null || phone.isBlank()) throw new IllegalArgumentException("El número telefónico no puede estar vacío.");
        if(!phone.matches("\\d{9}")) throw new IllegalArgumentException("El número telefónico debe ser de 9 cifras.");
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Cliente: " + this.name + " " + this.lastName + "\n" +
                "  - Email: " + this.email + "\n" +
                "  - Teléfono: " + this.phone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
