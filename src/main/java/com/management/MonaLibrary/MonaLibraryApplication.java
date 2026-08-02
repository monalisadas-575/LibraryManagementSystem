package com.management.MonaLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(escapeCharacter = '*')
@EntityScan(basePackages = "com.management.MonaLibrary.Entity")
public class MonaLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonaLibraryApplication.class, args);
	}

}
