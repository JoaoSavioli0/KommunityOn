package com.kommunityon.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.kommunityon.website")  // Escaneia controllers, services e componentes
@EntityScan("com.kommunityon.website.entities")  // Escaneia entidades JPA
@EnableJpaRepositories("com.kommunityon.website.repositories")  // Escaneia repositórios JPA
public class KommunityonApplication {

	public static void main(String[] args) {
		SpringApplication.run(KommunityonApplication.class, args);
	}

}
