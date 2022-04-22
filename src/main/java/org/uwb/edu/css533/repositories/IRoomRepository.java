package org.uwb.edu.css533.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.uwb.edu.css533.models.Room;
import org.uwb.edu.css533.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IRoomRepository extends PagingAndSortingRepository<Room, Long> {

    default List<Room> findRoomByTime(Date startTime, Date endTime){
        List<Room> rooms = new ArrayList<>();
        return rooms;
    }
}
