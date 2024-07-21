package com.goura.user.service.service.impl;

import com.goura.user.service.dto.UserDTO;
import com.goura.user.service.dto.UserDTOBuilder;
import com.goura.user.service.entitiy.Hotel;
import com.goura.user.service.entitiy.User;
import com.goura.user.service.exception.ResourceAlreadyExistsException;
import com.goura.user.service.exception.ResourceNotFoundException;
import com.goura.user.service.feign.HotelInterface;
import com.goura.user.service.repository.UserRepository;
import com.goura.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private HotelInterface hotelService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void createUser(User user) throws ResourceAlreadyExistsException {
        UserDTO userDTO = UserDTOBuilder.from(user);
        userDTO.setPassword(encoder.encode(user.getPassword()));
        try {
            repository.save(userDTO);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExistsException(
                    String.format("User with email: [%s] already exists.", user.getEmail())
            );
        }
        user.setUserId(userDTO.getUserId());
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll().stream()
                .map(UserDTOBuilder::from)
                .toList();
    }

    @Override
    public User getUser(String id) throws ResourceNotFoundException {
        UserDTO userDTO = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User with id:%s not found.", id)
                ));
        return UserDTOBuilder.from(userDTO);
    }

    @Override
    public User findByEmail(String email) throws ResourceNotFoundException {
        UserDTO userDTO = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User with id:%s not found.", email)
                ));
        return UserDTOBuilder.from(userDTO);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelService.getAll().getBody();
    }
}
