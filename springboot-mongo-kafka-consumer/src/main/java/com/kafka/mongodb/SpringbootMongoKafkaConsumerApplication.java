package com.kafka.mongodb;

import com.kafka.mongodb.configuration.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Import({SwaggerConfig.class})
public class SpringbootMongoKafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongoKafkaConsumerApplication.class, args);
	}

}
