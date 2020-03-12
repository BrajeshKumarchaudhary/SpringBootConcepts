package com.feed.feed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.feed.controller.FeedGenerator;

@SpringBootApplication
@ComponentScan("com.feed")
@EnableScheduling
public class FeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedApplication.class, args);
		FeedGenerator.genrateFeed();
	}

}
