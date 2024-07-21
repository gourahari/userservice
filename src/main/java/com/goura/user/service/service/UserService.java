package com.goura.user.service.service;

import com.goura.user.service.entitiy.Hotel;
import com.goura.user.service.entitiy.User;
import com.goura.user.service.exception.ResourceAlreadyExistsException;
import com.goura.user.service.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    void createUser(User user) throws ResourceAlreadyExistsException;
    List<User> getAllUsers();
    User getUser(String id) throws ResourceNotFoundException;
    List<Hotel> getAllHotels();
    User findByEmail(String email) throws ResourceNotFoundException;
}
