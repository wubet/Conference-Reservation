package org.uwb.edu.css533.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.uwb.edu.css533.models.Reservation;
import org.uwb.edu.css533.models.Room;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}
