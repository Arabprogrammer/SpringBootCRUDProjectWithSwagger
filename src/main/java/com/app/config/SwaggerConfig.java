package com.app.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.app"))
				.paths(PathSelectors.any()).build()
				.apiInfo(getInfo());
	}

	private ApiInfo getInfo() {
		Contact contact = new Contact("Arabprogrammer", "https://www.facebook.com/", "Arabprogrammer3030@gmail.com");
		return new ApiInfo("Spring Boot CRUD Project With Swagger", "Product Controller", "0.1", "https://Terms-of-service/",
				contact, "Licence to Arabprogrammer", "https://Licence/", new ArrayList<>());
	}
	
}
