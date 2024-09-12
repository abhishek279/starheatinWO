package com.starheatingWO.starheatingWO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StarheatingWoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarheatingWoApplication.class, args);
	}

}
