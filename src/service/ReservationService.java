package service;

import entity.Reservation;
import service.exception.DuplicateReservationException;
import repository.ReservationRepository;

import java.util.List;

public class ReservationService implements IReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void saveReservation(Reservation reservation) throws DuplicateReservationException {
        if(reservationRepository.exists(reservation)) {
            throw new DuplicateReservationException("Ya existe una reserva para este cliente en la misma fecha y hora.");
        }

        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getActiveReservations() {
        return reservationRepository.findAllActives();
    }

    @Override
    public List<Reservation> getReservationsByCustomerName(String name) {
        return reservationRepository.findAllByCustomerName(name);
    }

    @Override
    public void cancelReservationByCustomerName(String name) {
        reservationRepository.findByCustomerName(name)
                .ifPresentOrElse(
                        r -> r.setActive(false),
                        () -> {
                            throw new RuntimeException("Reservación no encontrada");
                        });
    }
}
