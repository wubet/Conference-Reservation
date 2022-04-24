package org.uwb.edu.css533.interfaces;

import org.springframework.data.domain.Page;
import org.uwb.edu.css533.models.Room;
import org.uwb.edu.css533.models.User;

import java.util.Date;
import java.util.List;

public interface IUserService {
    Page<User> listAllUsers(int page, int pageSize);
    User findUser(Long id);
    User createUser(User user);
    User updateUser(User user, Long id);
    void deleteUser(Long id);
}
