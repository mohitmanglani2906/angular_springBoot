package com.mohit2906;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
public class TodoRestServiceSpringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TodoRestServiceSpringApplication.class, args);
	}
	
	@Bean
	public WebClient.Builder getWebClientObject(){
		return WebClient.builder();
	}
}
