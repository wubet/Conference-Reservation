package org.uwb.edu.css533.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uwb.edu.css533.exception.ApplicationNotFoundException;
import org.uwb.edu.css533.interfaces.IUserService;
import org.uwb.edu.css533.models.User;
import org.uwb.edu.css533.repositories.IUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    public List<User> listAllUsers(){
        List<User> optionalUsers = null;
        try{
            optionalUsers = userRepository.findAll();
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return optionalUsers;
    }

    public User findUser(Long id){
        User user = null;
        try{
            Optional<User> optionalUser = userRepository.findById(id);
            if(optionalUser.isPresent()){
                user = optionalUser.get();
            }
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return user;
    }

    public User createUser(User user){
        User newUser = null;
        try{
            if(user != null){
                newUser = userRepository.saveAndFlush(user);
            }
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return newUser;
    }

    public User updateUser(User user, Long id){
        User updatedRoom = null;
        try{
            User existingUser =  userRepository.getById(id);
            BeanUtils.copyProperties(user, existingUser, "user_id");
            updatedRoom = userRepository.saveAndFlush(existingUser);
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return updatedRoom;
    }

    public void deleteUser(Long id){
        try{
            userRepository.deleteById(id);
        }
        catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
    }
}
