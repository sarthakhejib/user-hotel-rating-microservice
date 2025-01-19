package com.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This will work as a Centralized Gateway, which will be accessed by client.
 * It has all the configuration of all the microservices like ports, endpoint url etc.
 * We will use port 8085 and you can access any of the endpoints.
 * We can perform all CRUD operations with this.
 * WOW WOW WOW!!!
 */

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
