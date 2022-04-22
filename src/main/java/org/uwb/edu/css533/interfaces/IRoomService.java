package org.uwb.edu.css533.interfaces;

import org.springframework.data.domain.Page;
import org.uwb.edu.css533.models.Room;

import java.util.List;

public interface IRoomService {
    Page<Room> listAllRooms(int pageSize, int page);
    Page<Room> listAllRooms(int pageSize);
    Room findRoom(Long id);
    Room createRoom(Room room);
    Room updateRoom(Room room, Long id);
    void deleteRoom(Long id);
}
