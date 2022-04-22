package org.uwb.edu.css533.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.uwb.edu.css533.exception.ApplicationNotFoundException;
import org.uwb.edu.css533.models.Room;
import org.uwb.edu.css533.models.User;
import org.uwb.edu.css533.services.RoomService;

import java.util.List;


@RestController
@RequestMapping("api/v1/rooms")
public class RoomsController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<Page<Room>> list(@RequestParam(value = "pageSize") int pageSize, @RequestParam(required = false) Integer page){
        Page<Room> rooms = null;
        try{
            if(page == null)
                rooms = roomService.listAllRooms(pageSize);
            else
                rooms = roomService.listAllRooms(pageSize, page);
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
            return new ResponseEntity( HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
