package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RoomWorkManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomWorkManagementApplication.class, args);
	}

}
