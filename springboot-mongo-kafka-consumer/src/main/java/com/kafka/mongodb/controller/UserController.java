package com.kafka.mongodb.controller;

import com.kafka.mongodb.entity.User;
import com.kafka.mongodb.service.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserServiceImplement userService;

    @GetMapping("/consumer/all")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, users.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/consumer/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable long id) {
        Optional<User> optUser = userService.getUsersById(id);
        if (optUser.isPresent())
            return new ResponseEntity<>(optUser.get(), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
