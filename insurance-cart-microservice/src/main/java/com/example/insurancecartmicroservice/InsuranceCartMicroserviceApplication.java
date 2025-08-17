package com.example.insurancecartmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com.example.entity")
@EnableJpaRepositories("com.example.repo")
@EnableEurekaClient
public class InsuranceCartMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceCartMicroserviceApplication.class, args);
	}

}
