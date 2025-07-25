package repository;

import entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationRepository {
    void save(Reservation reservation);
    List<Reservation> findAllActives();
    List<Reservation> findAllByCustomerName(String name);
    Optional<Reservation> findByCustomerName(String name);
    boolean exists(Reservation reservation);
}
