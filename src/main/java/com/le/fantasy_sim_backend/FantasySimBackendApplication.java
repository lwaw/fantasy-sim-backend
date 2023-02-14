package com.le.fantasy_sim_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FantasySimBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantasySimBackendApplication.class, args);
	}

}
