package com.finwise.backend;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinWiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinWiseApplication.class, args);}

	@Bean
	public OpenAPI openApi() {
		Info inform = new Info()
				.description("GenAI-powered Financial Assistant for Smart Investing")
				.title("FinWise - AI Investment Advisor")
				.summary("API Documentation for FinWise Financial Assistant Project")
				.version("1.0.0");
		return new OpenAPI().info(inform);
	}



}
