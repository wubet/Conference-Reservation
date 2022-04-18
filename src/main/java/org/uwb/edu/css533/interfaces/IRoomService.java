package org.uwb.edu.css533.interfaces;

import org.uwb.edu.css533.models.Room;

import java.util.List;

public interface IRoomService {
    List<Room> listAllRooms();
    Room findRoom(Long id);
    Room createRoom(Room room);
    Room updateRoom(Room room, Long id);
    void deleteRoom(Long id);
}
