package org.uwb.edu.css533.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.uwb.edu.css533.models.Reservation;
import org.uwb.edu.css533.models.Room;
import org.uwb.edu.css533.repositories.IRoomRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class RoomServiceTest {

    private static final String FILE_PATH = "/services";
    private static final String ABSOLUTE_FILE_PATH = "src/test/resources/org/uwb/edu/css533/services/";

    @Mock
    IRoomRepository iRoomRepository;

    @InjectMocks
    RoomService roomService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void listAllRoomsTest() throws JsonProcessingException {

        List<Room> rmList = new ArrayList<Room>();
        ObjectMapper mapper = new ObjectMapper();
        //Reservation rm = mapper.readValue(ABSOLUTE_FILE_PATH + "room-test-data", Reservation.class);
        Page<Room> rmTestData = new PageImpl<>(rmList);
        //Page<Room> rooms =  roomService.listAllRooms(0, 5);

        //Create the mock object of stock service
        //IRoomRepository iRoomRepositoryMock = mock(IRoomRepository.class);

        // mock the behavior of stock service to return the value of various stocks
        //when(iRoomRepositoryMock.findAll()).thenReturn(any());

    }
}
