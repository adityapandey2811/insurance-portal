package com.example.FeedbackMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com.example.entity")
@EnableJpaRepositories("com.example.repo")
@EnableEurekaClient
@OpenAPIDefinition(info = @Info(title = "Feedback", description = "Give Feedback", version = ""))
public class FeedbackMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackMicroserviceApplication.class, args);
	}

}
