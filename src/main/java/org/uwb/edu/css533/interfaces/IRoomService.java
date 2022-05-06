package org.uwb.edu.css533.interfaces;

import org.springframework.data.domain.Page;
import org.uwb.edu.css533.models.Room;

import java.util.Date;
import java.util.List;

public interface IRoomService {
    Page<Room> listAllRooms(int page, int pageSize);
    Page<Room> findRoomsByTime(Date startTime, Date endTime, int page, int pageSize);
    Room findRoom(Long id);
    Room createRoom(Room room);
    Room updateRoom(Room room, Long id);
    void deleteRoom(Long id);
//    Page<Room> findBookedRoomsByDate(Date meetingDate, Integer page, int size);
}
