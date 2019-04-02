package com.currency.converter.convert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConvertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConvertApplication.class, args);
	}

}

