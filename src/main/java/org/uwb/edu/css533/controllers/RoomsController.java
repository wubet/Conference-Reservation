package org.uwb.edu.css533.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.uwb.edu.css533.exception.ApplicationNotFoundException;
import org.uwb.edu.css533.models.Room;
import org.uwb.edu.css533.models.User;
import org.uwb.edu.css533.services.RoomService;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/v1/rooms")
public class RoomsController {

    @Autowired
    private RoomService roomService;

    @Value("${paging.default.pageSize}")
    private int size;

    @GetMapping
    public ResponseEntity<Page<Room>> listRooms(@RequestParam(value = "page" ) Integer page,
                                                @RequestParam(required = false) Integer pageSize){
        Page<Room> rooms = null;
        try{
            if(pageSize == null)
                rooms = roomService.listAllRooms(page, size);
            else
                rooms = roomService.listAllRooms(page, pageSize);
            return new ResponseEntity<Page<Room>>(rooms, HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping
    @RequestMapping("/availability")
    public ResponseEntity<Page<Room>> listByTime(@RequestParam(value = "startTime") Date startTime,
                                                 @RequestParam(value = "endTime") Date endTime,
                                                 @RequestParam(value = "pageSize") Integer page,
                                                 @RequestParam(required = false) Integer pageSize){
        Page<Room> rooms = null;
        try{
            if(pageSize == null)
                rooms = roomService.findRoomsByTime(startTime, endTime, page, size);
            else
                rooms = roomService.findRoomsByTime(startTime, endTime, page, pageSize);
            return new ResponseEntity<Page<Room>>(rooms, HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Room> create(@RequestBody final Room room){
        try{
            Room newRoom = roomService.createRoom(room);
            return new ResponseEntity<Room>(newRoom, HttpStatus.CREATED);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<Room> get(@PathVariable("id") Long id){
        try{
            return new ResponseEntity<Room>(roomService.findRoom(id), HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody final Room room){
        try{
            Room newRoom = roomService.updateRoom(room, id);
            return new ResponseEntity<Room>(newRoom, HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id){
        try{
            roomService.deleteRoom(id);
            return new ResponseEntity( HttpStatus.NO_CONTENT);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
