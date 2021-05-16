package com.kafka.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootMongoKafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongoKafkaProducerApplication.class, args);
	}

}
