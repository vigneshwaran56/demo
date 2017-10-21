package com.concretepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement

public class ApplicationStarter extends SpringBootServletInitializer {  
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
		
    }       
}            