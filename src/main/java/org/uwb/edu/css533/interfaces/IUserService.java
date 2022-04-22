package org.uwb.edu.css533.interfaces;

import org.springframework.data.domain.Page;
import org.uwb.edu.css533.models.User;

import java.util.List;

public interface IUserService {
    Page<User> listAllUsers(int pageSize, int page);
    Page<User> listAllUsers(int pageSize);
    User findUser(Long id);
    User createUser(User user);
    User updateUser(User user, Long id);
    void deleteUser(Long id);
}
