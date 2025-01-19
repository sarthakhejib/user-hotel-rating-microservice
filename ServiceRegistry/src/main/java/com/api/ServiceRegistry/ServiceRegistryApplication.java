package com.api.ServiceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 *The whole idea is we will use Eureka server as a server, which will keep track on the status of all
 * the other microservices. They are up or down, port to access etc. We will register the other microservices as a CLIENT,
 * so that they can interact with Eureka.
 *
 * We can simply access Eureka server(Which is like UI) on the port 8084
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}
