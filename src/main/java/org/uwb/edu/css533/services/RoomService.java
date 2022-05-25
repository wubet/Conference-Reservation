package org.uwb.edu.css533.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.uwb.edu.css533.exception.ApplicationNotFoundException;
import org.uwb.edu.css533.interfaces.IRoomService;
import org.uwb.edu.css533.models.Room;
import org.uwb.edu.css533.repositories.IRoomRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private IRoomRepository roomRepository;

    /**
     *
     * @param page
     * @param pageSize
     * @return
     */
    public Page<Room> listAllRooms(int page, int pageSize){

        Pageable PageWithElements = PageRequest.of(page, pageSize);
        Page<Room> optionalRooms = null;
        try{
            optionalRooms = roomRepository.findAll(PageWithElements);
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return optionalRooms;
    }

    /**
     *
     * @param startTime
     * @param endTime
     * @param page
     * @param pageSize
     * @return
     */
    public Page<Room> findRoomsByTime(Date startTime, Date endTime, int page, int pageSize){

        Pageable PageWithElements = PageRequest.of(page, pageSize);
        Page<Room> optionalRooms = null;
        try{
            optionalRooms = roomRepository.findRoomByTime(startTime, endTime, PageWithElements);
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return optionalRooms;
    }

    /**
     *
     * @param id
     * @return
     */
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

    /**
     *
     * @param room
     * @return
     */
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

    /**
     *
     * @param room
     * @param id
     * @return
     */
    public Room updateRoom(Room room, Long id){
        Room updatedRoom = null;
        try{
            Room existingRoom =  roomRepository.getById(id);
            BeanUtils.copyProperties(room, existingRoom, "room_id");
            updatedRoom = roomRepository.saveAndFlush(existingRoom);
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return updatedRoom;
    }

    /**
     *
     * @param id
     */
    public void deleteRoom(Long id){
        try{
            roomRepository.deleteById(id);
        }
        catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
    }
}
