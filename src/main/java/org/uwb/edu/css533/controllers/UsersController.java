package org.uwb.edu.css533.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.uwb.edu.css533.exception.ApplicationNotFoundException;
import org.uwb.edu.css533.models.Room;
import org.uwb.edu.css533.models.User;
import org.uwb.edu.css533.services.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Value("${paging.default.pageSize}")
    private int size;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Page<User>> list(@RequestParam(value = "page") Integer page,
//                                           @RequestParam(required=false) String sortKey,
                                           @RequestParam(required = false) Integer pageSize){
        Page<User> users = null;
        try{
            if(pageSize == null)
                users = userService.listAllUsers(page, size);
            else
                users = userService.listAllUsers(page, pageSize);
            return new ResponseEntity<Page<User>>(users, HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@RequestBody final User user){
        try{
            User newUser = userService.createUser(user);
            return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping
    @RequestMapping("/check/{user_name}")
    public ResponseEntity<User> checkuser(@PathVariable("user_name") String userName){
        try{
            return new ResponseEntity<User>(userService.findByUserName(userName), HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<User> get(@PathVariable("id") Long id){
        try{
            return new ResponseEntity<User>(userService.findUser(id), HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @CrossOrigin
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody final User user){
        try{
            User newUser = userService.updateUser(user, id);
            return new ResponseEntity<User>(newUser, HttpStatus.OK);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @CrossOrigin
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id){
        try{
            userService.deleteUser(id);
            return new ResponseEntity( HttpStatus.NO_CONTENT);
        } catch(ApplicationNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
