package org.uwb.edu.css533.interfaces;

import org.uwb.edu.css533.models.User;

import java.util.List;

public interface IUserService {
    List<User> listAllUsers();
    User findUser(Long id);
    User createUser(User user);
    User updateUser(User user, Long id);
    void deleteUser(Long id);
}
