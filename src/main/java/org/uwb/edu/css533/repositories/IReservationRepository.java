package org.uwb.edu.css533.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uwb.edu.css533.models.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}
