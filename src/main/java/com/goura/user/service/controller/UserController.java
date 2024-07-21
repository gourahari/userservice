package com.goura.user.service.controller;

import com.goura.user.service.entitiy.Hotel;
import com.goura.user.service.entitiy.User;
import com.goura.user.service.exception.ResourceAlreadyExistsException;
import com.goura.user.service.exception.ResourceNotFoundException;
import com.goura.user.service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody @Valid User user) throws Exception {
        service.createUser(user);
        return ResponseEntity.ok().body(String.format("Created with id: %s", user.getUserId()));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(service.getAllUsers());
    }

    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok().body(service.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(service.getUser(id));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                e.getBindingResult().getFieldError().getDefaultMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
