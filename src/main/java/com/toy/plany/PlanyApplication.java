package com.toy.plany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PlanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanyApplication.class, args);
	}

}
