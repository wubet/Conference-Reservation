package org.uwb.edu.css533.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.uwb.edu.css533.exception.ApplicationNotFoundException;
import org.uwb.edu.css533.models.Reservation;
import org.uwb.edu.css533.models.Room;
import org.uwb.edu.css533.services.ReservationService;

import java.util.List;

@RestController
@RequestMapping("api/v1/reservations")
public class ReservationsController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> list(){
        try{
            List<Reservation> reservations = reservationService.listAllReservations();
            return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Reservation> create(@RequestBody final Reservation reservation){
        try{
            Reservation newReservation = reservationService.createReservation(reservation);
            return new ResponseEntity<Reservation>(newReservation, HttpStatus.CREATED);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<Reservation> get(@PathVariable("id") Long id){
        try{
            return new ResponseEntity<Reservation>(reservationService.findReservation(id), HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Reservation> update(@PathVariable Long id, @RequestBody final Reservation reservation){
        try{
            Reservation newReservation = reservationService.updateReservation(reservation, id);
            return new ResponseEntity<Reservation>(newReservation, HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id){
        try{
            reservationService.deleteReservation(id);
            return new ResponseEntity( HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}