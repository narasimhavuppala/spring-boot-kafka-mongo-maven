package com.kafka.mongodb.service;

import com.kafka.mongodb.repository.UserRepository;
import com.kafka.mongodb.entity.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService{

    @Autowired
    private UserRepository userRepository;

   // @KafkaListener(topics = "myTopic", groupId = "user_group", containerFactory = "userKafkaListenerFactory")
    public void consumeUser1(ConsumerRecord<String, User> consumerRecord) {
        User user = consumerRecord.value();
        System.out.println("Consumer 1:Consumed User JSON Message: " + user.toString());
        user.setConsumer(true);
        userRepository.save(consumerRecord.value());
        System.out.println(consumerRecord);
    }

    @KafkaListener(topics = "myTopic", groupId = "user_group", containerFactory = "userKafkaListenerFactory")
    public void consumeUser2(ConsumerRecord consumerRecord) {
        System.out.println(Thread.currentThread().getName() + "-Consumer 2:Consumed User JSON Message: "
                + consumerRecord.value());
    }

    public List<User> getAllUsers() {
        List<User> user = userRepository.findAll();
        return user;
    }

    public Optional<User> getUsersById(long id) {
       return userRepository.findById(id);
    }
}
