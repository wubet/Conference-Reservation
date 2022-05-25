package org.uwb.edu.css533.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.uwb.edu.css533.models.Room;

import java.util.Date;

public interface IRoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM rooms r " +
            "JOIN r.reservations rs " +
            "WHERE rs.start >= :endTime " +
            "AND rs.end <= :startTime " +
            "AND r.status = 'active' ")
    Page<Room> findRoomByTime(Date startTime, Date endTime, Pageable pageable);

}
