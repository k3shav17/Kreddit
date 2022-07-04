package com.kreddit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KredditApplication {

	public static void main(String[] args) {
		SpringApplication.run(KredditApplication.class, args);
	}

}
