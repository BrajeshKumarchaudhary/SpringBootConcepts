package com.app.s3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.app.s3.*")
public class S3AwsStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3AwsStorageApplication.class, args);
	}

}
