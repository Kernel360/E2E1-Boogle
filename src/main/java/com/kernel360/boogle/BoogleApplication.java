package com.kernel360.boogle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BoogleApplication {
	public static void main(String[] args) {
		SpringApplication.run(BoogleApplication.class, args);
	}
}
