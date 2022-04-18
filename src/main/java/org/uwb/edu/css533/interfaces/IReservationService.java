package org.uwb.edu.css533.interfaces;

import org.uwb.edu.css533.models.Reservation;
import org.uwb.edu.css533.models.Room;

import java.util.List;

public interface IReservationService {
    List<Reservation> listAllReservations();
    Reservation findReservation(Long id);
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation, Long id);
    void deleteReservation(Long id);
}
