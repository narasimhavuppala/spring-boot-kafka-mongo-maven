package com.kafka.mongodb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.mongodb.repository.UserRepository;
import com.kafka.mongodb.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.UUID;

@Service

public class UserServiceImplement implements UserService {

    private static final String topicName = "myTopic";

    Log logger = LogFactory.getLog(UserServiceImplement.class);

    double salary = 11;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public List<User> getAllUsers() {
        List<User> user = userRepository.findAll();
        return user;
    }


    public String sendSimpleMessage(User user) throws JsonProcessingException {
        logger.info(user.toString());
        userRepository.save(user);

        ObjectMapper mapper=new ObjectMapper();

        Message<User> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .setHeader("X-Custom-Header", "custom Header")
                .build();

        ListenableFuture<SendResult<String, User>> future = kafkaTemplate.send(message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
            public void onSuccess(SendResult<String, User> result) {
                System.out.println("Sent message=[" + user.getName() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + user.getName() + "] due to : " + ex.getMessage());
            }
        });
        return "Success";
    }

    public String sendCallbackMessage(User user) {
        userRepository.save(user);
        ListenableFuture<SendResult<String, User>> future = kafkaTemplate.send(topicName, user);
        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
            public void onSuccess(SendResult<String, User> result) {
                System.out.println("Sent message=[" + user.getName() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + user.getName() + "] due to : " + ex.getMessage());
            }
        });
        return "Success";
    }

    @Scheduled(fixedDelay = 1000)
    public void sendSampleMessages() throws JsonProcessingException {
        User user = new User();
        UUID random = UUID.randomUUID();
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        user.setDept("depart-" + random.toString());
        user.setSalary(++this.salary);
        user.setName("Name-" + random.toString());
        this.sendSimpleMessage(user);
    }
}

