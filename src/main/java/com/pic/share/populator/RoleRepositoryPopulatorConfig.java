package com.pic.share.populator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
public class RoleRepositoryPopulatorConfig {
	Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
	
	@Bean
	public Jackson2RepositoryPopulatorFactoryBean rolePopulatorFactoryBean() {
	    //factory.setResources(new Resource[]{new ClassPathResource("role-data.json")});
	    return factory;
	}
	
}
