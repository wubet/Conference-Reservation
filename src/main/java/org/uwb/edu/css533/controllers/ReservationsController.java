package org.uwb.edu.css533.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.uwb.edu.css533.exception.ApplicationNotFoundException;
import org.uwb.edu.css533.models.Reservation;
import org.uwb.edu.css533.models.Room;
import org.uwb.edu.css533.services.ReservationService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/reservations")
public class ReservationsController {

    @Autowired
    private ReservationService reservationService;

    @Value("${paging.default.pageSize}")
    private int size;

    @GetMapping
    public ResponseEntity<Page<Reservation>> list(@RequestParam(value = "page" ) Integer page,
                                                  @RequestParam( required = false) Integer pageSize){
        Page<Reservation> reservations = null;
        try{
            if(pageSize == null)
                reservations = reservationService.listAllReservations(page, size);
            else
                reservations = reservationService.listAllReservations(page, pageSize);
            return new ResponseEntity<Page<Reservation>>(reservations, HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }


    @GetMapping
    @RequestMapping("/booked")
    public ResponseEntity<Page<Reservation>> listByDate(@RequestParam(value = "meetingDate") Date meetingDate,
                                                        @RequestParam(value = "rangeDate") Date rangeDate,
                                                        @RequestParam(value = "pageSize") Integer page,
                                                        @RequestParam(required = false) Integer pageSize){
        Page<Reservation> reservations = null;
        try{
            if(pageSize == null)
                reservations = reservationService.findReservationByDate(meetingDate, rangeDate, page, size);
            else
                reservations = reservationService.findReservationByDate(meetingDate, rangeDate, page, pageSize);
            return new ResponseEntity<Page<Reservation>>(reservations, HttpStatus.OK);
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
            return new ResponseEntity( HttpStatus.NO_CONTENT);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
