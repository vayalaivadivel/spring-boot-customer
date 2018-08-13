package org.sample.customer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author vadivel
 *
 */
@SpringBootApplication
public class SpringBootJpaApplication {
	private static final Logger LOG = LoggerFactory.getLogger(SpringBootJpaApplication.class);

	public static void main(String[] args) {
		LOG.info("Started to initialize spring boot application");
		SpringApplication.run(SpringBootJpaApplication.class, args);
		LOG.info("Spring boot application initialized successfully");
	}
}
