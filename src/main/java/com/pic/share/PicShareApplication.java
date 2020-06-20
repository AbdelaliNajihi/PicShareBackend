package com.pic.share;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.support.MultipartFilter;

@SpringBootApplication
public class PicShareApplication {
	public static void main(String[] args) {
		SpringApplication.run(PicShareApplication.class, args);
	}

	@Bean
	public MultipartFilter multipartFilter(){

	    MultipartFilter multipartFilter = new MultipartFilter();
	    multipartFilter.setMultipartResolverBeanName("multipartResolver");
	    return multipartFilter;
	}
}
