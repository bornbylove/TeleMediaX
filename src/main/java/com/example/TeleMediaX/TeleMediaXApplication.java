package com.example.TeleMediaX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class TeleMediaXApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeleMediaXApplication.class, args);
	}

}
