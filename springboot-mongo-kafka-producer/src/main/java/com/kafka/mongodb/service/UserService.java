package com.kafka.mongodb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.mongodb.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public String sendSimpleMessage(User user) throws JsonProcessingException;
    public String sendCallbackMessage(User user);
}
