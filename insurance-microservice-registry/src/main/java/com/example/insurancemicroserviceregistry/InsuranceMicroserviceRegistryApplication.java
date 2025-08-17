package com.example.insurancemicroserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class InsuranceMicroserviceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceMicroserviceRegistryApplication.class, args);
	}

}
