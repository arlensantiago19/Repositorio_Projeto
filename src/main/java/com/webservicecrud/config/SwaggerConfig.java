package com.webservicecrud.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.webservicecrud"))
				.paths(regex("/Clientes.*"))
				.build()
				.apiInfo(metaInfo());

	}
	
	private ApiInfo metaInfo() {
		@SuppressWarnings("rawtypes")
		ApiInfo apiInfo = new ApiInfo(
				"Clientes API REST",
				"API REST de cadastro de clientes.",
				"1.0",
				"Terms of Service",
				new Contact("Arlen Santiago", "arlensantiago2616@gmail.com", null),
				"Apache License Version 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>()
				);
		
		return apiInfo;
	}

}


