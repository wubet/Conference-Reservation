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
import org.uwb.edu.css533.models.User;
import org.uwb.edu.css533.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private static final String FILE_PATH = "/services";
    private static final String ABSOLUTE_FILE_PATH = "src/test/resources/org/uwb/edu/css533/services/";

    @Mock
    IUserRepository iUserRepository;

    @InjectMocks
    UserService userService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void listAllUsersTest() throws JsonProcessingException {

        List<User> usList = new ArrayList<User>();
        ObjectMapper mapper = new ObjectMapper();
        //User us = mapper.readValue(ABSOLUTE_FILE_PATH + "user-test-data", User.class);
        Page<User> usTestData = new PageImpl<>(usList);
        //Page<User> users =  userService.listAllUsers(0, 5);

        //Create the mock object of stock service
        //IUserRepository iUserRepository = mock(IUserRepository.class);

        // mock the behavior of stock service to return the value of various stocks
        //when(iUserRepository.findAll()).thenReturn(any());
    }
}
