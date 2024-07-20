package com.learning.central_configuration_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CentralConfigurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralConfigurationServerApplication.class, args);
	}

}
