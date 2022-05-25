package org.uwb.edu.css533.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.uwb.edu.css533.models.Reservation;
import org.uwb.edu.css533.models.Room;

import java.util.Date;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT rs FROM Reservations AS rs " +
            "WHERE rs.start between :startDate AND :endDate " +
            "AND rs.status = 'Active' ")
    Page<Reservation> findReservationsByDate(Date startDate, Date endDate, Pageable pageWithElements);


    @Query("SELECT rs FROM Reservations AS rs " +
            "JOIN rs.room AS r " +
            "WHERE r.room_id = ?1 " +
            "AND rs.start >= ?2 " +
            "AND rs.end <= ?3 " +
            "AND rs.status = 'active' ")
    Page<Reservation> findBookedRoomsByDateAndRoom(Long roomId, Date startDate, Date endDate, Pageable pageWithElements);
}
