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

    @Query(value = "SELECT rs FROM reservations rs " +
            "WHERE rs.meeting_start_time between :meetingDate AND :rangeDate " +
            "AND rs.status == 'Active' ",
            nativeQuery = true  )
    Page<Reservation> findBookedRoomsByDate(Date meetingDate, Date rangeDate, Pageable pageWithElements);
}
