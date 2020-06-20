package com.pic.share.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo());
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfo("PicShare",
				"PicShare API Description",
				"version 1.0",
				"",
				new Contact("Abdelali Najihi", "https://github.com/AbdelaliNajihi", ""),
				"",
				"", Collections.emptyList());
	}

}
