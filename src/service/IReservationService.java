package service;

import entity.Reservation;

import java.util.List;

public interface IReservationService {
    void saveReservation(Reservation reservation) throws Exception;
    List<Reservation> getActiveReservations();
    List<Reservation> getReservationsByCustomerName(String name);
    void cancelReservationByCustomerName(String name);
}
