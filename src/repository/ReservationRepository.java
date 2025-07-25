package repository;

import entity.Reservation;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ReservationRepository implements IReservationRepository{
    // No permite almacenar reservaciones duplicadas
    private static Set<Reservation> reservations;

    public ReservationRepository() {
        reservations = new LinkedHashSet<>();
    }

    @Override
    public void save(Reservation reservation) {
        reservations.add(reservation);
    }

    @Override
    public List<Reservation> findAllActives() {
        return reservations.stream()
                .filter(Reservation::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findAllByCustomerName(String name) {
        return reservations.stream()
                .filter(r -> r.getCustomer().getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reservation> findByCustomerName(String name) {
        return reservations.stream()
                .filter(r -> r.getCustomer().getName().equalsIgnoreCase(name) && r.isActive())
                .findFirst();
    }

    @Override
    public boolean exists(Reservation reservation) {
        return reservations.contains(reservation);
    }
}
