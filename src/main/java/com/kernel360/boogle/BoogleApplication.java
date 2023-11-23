package com.kernel360.boogle;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableBatchProcessing
public class BoogleApplication {
	public static void main(String[] args) {
		SpringApplication.run(BoogleApplication.class, args);
	}
}
