package org.uwb.edu.css533.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    /**
     *
     * @param page
     * @param pageSize
     * @return
     */
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

    /**
     *
     * @param startDate
     * @param endDate
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<Reservation> findReservationsByDate(Date startDate, Date endDate, Integer page, int pageSize) {
        Pageable PageWithElements = PageRequest.of(page, pageSize);
        Page<Reservation> optionalReservations = null;
        try{
            optionalReservations = reservationRepository.findReservationsByDate(startDate, endDate, PageWithElements);
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return optionalReservations;
    }

    /**
     *
     * @param roomId
     * @param startDate
     * @param endDate
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<Reservation> findReservationByDateAndRoom(Long roomId, Date startDate, Date endDate, Integer page, int pageSize) {
        Pageable PageWithElements = PageRequest.of(page, pageSize);
        Page<Reservation> optionalReservations = null;
        try{
            optionalReservations = reservationRepository.findBookedRoomsByDateAndRoom(roomId, startDate, endDate, PageWithElements);
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return optionalReservations;
    }


    /**
     *
     * @param id
     * @return
     */
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

    /**
     *
     * @param reservation
     * @return
     */
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


    /**
     *
     * @param reservation
     * @param id
     * @return
     */
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

    /**
     *
     * @param id
     */
    public void deleteReservation(Long id){
        try{
            reservationRepository.deleteById(id);
        }
        catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
    }
}
