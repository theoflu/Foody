package com.yasu.Foody;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Foody APIs", version = "1.0", description = "Documentation of Foody APIs v1.0"))
@EnableCaching
public class FoodyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodyApplication.class, args);
	}


}
