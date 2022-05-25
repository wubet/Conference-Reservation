package org.uwb.edu.css533.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.uwb.edu.css533.models.Reservation;
import org.uwb.edu.css533.repositories.IReservationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservationServiceTest {

    private static final String FILE_PATH = "/services";
    private static final String ABSOLUTE_FILE_PATH = "src/test/resources/org/uwb/edu/css533/services/";


    @Mock
    IReservationRepository reservationRepository;

    @InjectMocks
    ReservationService reservationService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void listAllReservationsTest() throws JsonProcessingException {

        List<Reservation> rsList = new ArrayList<Reservation>();
        ObjectMapper mapper = new ObjectMapper();
        //Reservation res = mapper.readValue(ABSOLUTE_FILE_PATH + "reservation-test-data", Reservation.class);
        Page<Reservation> rsTestData = new PageImpl<>(rsList);
        //Page<Reservation> reservations =  reservationService.listAllReservations(0, 5);

        //Create the mock object of stock service
        //IReservationRepository iReservationRepositoryMock = mock(IReservationRepository.class);

        //mock the behavior of stock service to return the value of various stocks
        //when(iReservationRepositoryMock.findAll()).thenReturn(any());

    }
}
