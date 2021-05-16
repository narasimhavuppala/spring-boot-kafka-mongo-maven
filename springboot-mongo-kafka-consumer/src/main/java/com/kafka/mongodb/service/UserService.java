package com.kafka.mongodb.service;

import com.kafka.mongodb.entity.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.List;

public interface UserService {
    void consumeUser1(ConsumerRecord<String, User> consumerRecord);
    void consumeUser2(ConsumerRecord consumerRecord);
    List<User> getAllUsers();
}
