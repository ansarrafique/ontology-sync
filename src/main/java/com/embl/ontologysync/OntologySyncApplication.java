package com.embl.ontologysync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * The main application class for the OntologySync application.
 *
 * This class serves as the entry point for the OntologySync application.
 */
@SpringBootApplication
public class OntologySyncApplication {
	
	/**
	 * Creates and configures a RestTemplate bean.
	 *
	 * @return The configured RestTemplate instance.
	 */
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	/**
	 * The entry point of the OntologySync application.
	 *
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(OntologySyncApplication.class, args);
	}
}
