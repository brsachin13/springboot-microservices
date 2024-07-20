package com.learning.discoverey_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscovereyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscovereyServerApplication.class, args);
	}

}
