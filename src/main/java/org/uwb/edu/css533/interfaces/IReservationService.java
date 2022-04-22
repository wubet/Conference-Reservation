package org.uwb.edu.css533.interfaces;

import org.springframework.data.domain.Page;
import org.uwb.edu.css533.models.Reservation;
import org.uwb.edu.css533.models.Room;

import java.util.List;

public interface IReservationService {
    Page<Reservation> listAllReservations(int pageSize, int page);
    Page<Reservation> listAllReservations(int pageSize);
    Reservation findReservation(Long id);
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation, Long id);
    void deleteReservation(Long id);
}
