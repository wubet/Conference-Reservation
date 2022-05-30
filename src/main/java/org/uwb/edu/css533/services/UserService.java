package org.uwb.edu.css533.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.uwb.edu.css533.exception.ApplicationNotFoundException;
import org.uwb.edu.css533.interfaces.IUserService;
import org.uwb.edu.css533.models.User;
import org.uwb.edu.css533.repositories.IUserRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    /**
     *
     * @param page
     * @param pageSize
     * @return
     */
    public Page<User> listAllUsers(int page, int pageSize){

        Pageable PageWithElements = PageRequest.of(page, pageSize);

        Page<User> optionalUsers = null;
        try{
            optionalUsers = userRepository.findAll(PageWithElements);
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return optionalUsers;
    }

    /**
     *
     * @param id
     * @return
     */
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

    /**
     *
     * @param userName
     * @return
     */
    @Override
    public User findByUserName(String userName) {
        User user = null;
        try{
            Optional<User> optionalUser = userRepository.findByUserName(userName);
            if(optionalUser.isPresent()){
                user = optionalUser.get();
            }
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return user;
    }

    /**
     *
     * @param user
     * @return
     */
    public User createUser(User user){
        User newUser = null;
        try{
            if(user != null){
                user.setCreateDateTime(new Date());
                user.setUpdateDateTime(new Date());
                newUser = userRepository.saveAndFlush(user);
            }
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return newUser;
    }

    /**
     *
     * @param user
     * @param id
     * @return
     */
    public User updateUser(User user, Long id){
        User updatedRoom = null;
        try{
            User existingUser =  userRepository.getById(id);
            Date date = existingUser.getCreateDateTime();
            BeanUtils.copyProperties(user, existingUser, "user_id");
            existingUser.setCreateDateTime(date);
            existingUser.setUpdateDateTime(new Date());
            updatedRoom = userRepository.saveAndFlush(existingUser);
        }catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
        return updatedRoom;
    }

    /**
     *
     * @param id
     */
    public void deleteUser(Long id){
        try{
            userRepository.deleteById(id);
        }
        catch(Exception ex){
            throw new ApplicationNotFoundException(ex.getMessage());
        }
    }
}
