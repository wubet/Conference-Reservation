package org.uwb.edu.css533.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.uwb.edu.css533.exception.ApplicationNotFoundException;
import org.uwb.edu.css533.interfaces.IReservationService;
import org.uwb.edu.css533.models.Reservation;
import org.uwb.edu.css533.models.Room;
import org.uwb.edu.css533.repositories.IReservationRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    /*
    *
    * */
    public Page<Reservation> listAllReservations(int page, int pageSize){
        Pageable PageWithElements = PageRequest.of(page, pageSize);
        Page<Reservation> optionalReservations = null;
        try{
            optionalReservations = reservationRepository.findAll(PageWithElements);
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return optionalReservations;
    }

    /*
     *
     * */
    @Override
    public Page<Reservation> findReservationByDate(Date startDate, Date endDate, Integer page, int pageSize) {
        Pageable PageWithElements = PageRequest.of(page, pageSize);
        Page<Reservation> optionalReservations = null;
        try{
            optionalReservations = reservationRepository.findBookedRoomsByDate(startDate, endDate, PageWithElements);
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return optionalReservations;
    }

//    public Page<Reservation> listAllReservations(int pageSize){
//        Pageable PageWithElements = PageRequest.of(0, pageSize);
//        Page<Reservation> optionalReservations = null;
//        try{
//            optionalReservations = reservationRepository.findAll(PageWithElements);
//        }catch(Exception ex){
//            throw new ApplicationNotFoundException(ex.getMessage());
//        }
//        return optionalReservations;
//    }

    /*
    *
    * */
    public Reservation findReservation(Long id) {

        Reservation reservation = null;
        try {
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);
            if (optionalReservation.isPresent()) {
                reservation = optionalReservation.get();
            }
        } catch (Exception ex) {
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return reservation;
    }

    /*
    *
    * */
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

//    public Reservation updateReservation(Reservation reservation, Long id){
//        Reservation updatedReservation = null;
//        try{
//            Optional<Reservation> existingReservation =  reservationRepository.findById(id);
//            //BeanUtils.copyProperties(reservation, existingReservation, "reservation_id");
//            if(existingReservation != null)
//                reservation.setReservation_id(existingReservation.get().getReservation_id());
//            updatedReservation = reservationRepository.save(reservation);
//        }catch(Exception ex){
//            throw new ApplicationNotFoundException(ex.getMessage());
//        }
//        return updatedReservation;
//    }

    /*
    *
    * */
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

    /*
    * */
    public void deleteReservation(Long id){
        try{
            reservationRepository.deleteById(id);
        }
        catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
    }
}
