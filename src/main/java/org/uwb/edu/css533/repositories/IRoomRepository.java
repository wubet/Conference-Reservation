package org.uwb.edu.css533.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.uwb.edu.css533.models.Room;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IRoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "SELECT r FROM rooms r " +
            "JOIN fetch r.reservations reservation " +
            "WHERE reservation.meeting_start_time >= :endTime " +
            "AND reservation.meeting_end_time <= :startTime " +
            "AND r.status == 'Active' ",
             nativeQuery = true  )
    Page<Room> findRoomByTime(Date startTime, Date endTime, Pageable pageable);

// {
//        Page<Room> rooms = null;
//        return rooms;
//    }
}
