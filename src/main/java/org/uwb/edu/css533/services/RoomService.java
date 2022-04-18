package org.uwb.edu.css533.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uwb.edu.css533.exception.ApplicationNotFoundException;
import org.uwb.edu.css533.interfaces.IRoomService;
import org.uwb.edu.css533.models.Room;
import org.uwb.edu.css533.repositories.IRoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private IRoomRepository roomRepository;

    public List<Room> listAllRooms(){
        List<Room> optionalRooms = null;
        try{
            optionalRooms = roomRepository.findAll();
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return optionalRooms;
    }

    public Room findRoom(Long id){
        Room room = null;
        try{
            Optional<Room> optionalRoom = roomRepository.findById(id);
            if(optionalRoom.isPresent()){
                room = optionalRoom.get();
            }
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return room;
    }

    public Room createRoom(Room room){
        Room newRoom = null;
        try{
            if(room != null){
                newRoom = roomRepository.saveAndFlush(room);
            }
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return newRoom;
    }

    public Room updateRoom(Room room, Long id){
        Room updatedRoom = null;
        try{
            Room existingSession =  roomRepository.getById(id);
            BeanUtils.copyProperties(room, existingSession, "room_id");
            updatedRoom = roomRepository.saveAndFlush(existingSession);
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return updatedRoom;
    }

    public void deleteRoom(Long id){
        try{
            roomRepository.deleteById(id);
        }
        catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
    }
}
