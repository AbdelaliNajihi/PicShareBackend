package com.pic.share.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	@Value("${emailSender}")
	private String emailSender;
	@Value("${passwordSender}")
	private String passwordSender;
	
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("127.0.0.1");
		javaMailSenderImpl.setPort(25);
		javaMailSenderImpl.setUsername(emailSender);
		javaMailSenderImpl.setPassword(passwordSender);
		Properties props = javaMailSenderImpl.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
		return javaMailSenderImpl;
	}
}
