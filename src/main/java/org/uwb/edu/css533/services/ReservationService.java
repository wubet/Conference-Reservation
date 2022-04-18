package org.uwb.edu.css533.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uwb.edu.css533.exception.ApplicationNotFoundException;
import org.uwb.edu.css533.interfaces.IReservationService;
import org.uwb.edu.css533.models.Reservation;
import org.uwb.edu.css533.repositories.IReservationRepository;


import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    public List<Reservation> listAllReservations(){
        List<Reservation> optionalReservations = null;
        try{
            optionalReservations = reservationRepository.findAll();
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return optionalReservations;
    }

    public Reservation findReservation(Long id){
        Reservation reservation = null;
        try{
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);
            if(optionalReservation.isPresent()){
                reservation = optionalReservation.get();
            }
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return reservation;
    }

    public Reservation createReservation(Reservation reservation){
        Reservation newReservation = null;
        try{
            if(reservation != null){
                newReservation = reservationRepository.saveAndFlush(reservation);
            }
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return newReservation;
    }

    public Reservation updateReservation(Reservation reservation, Long id){
        Reservation updatedReservation = null;
        try{
            Reservation existingReservation =  reservationRepository.getById(id);
            BeanUtils.copyProperties(reservation, existingReservation, "reservation_id");
            updatedReservation = reservationRepository.saveAndFlush(existingReservation);
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return updatedReservation;
    }

    public void deleteReservation(Long id){
        try{
            reservationRepository.deleteById(id);
        }
        catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
    }
}
