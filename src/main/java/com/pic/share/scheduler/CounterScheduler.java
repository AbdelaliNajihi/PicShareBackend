package com.pic.share.scheduler;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.pic.share.service.IUserAppService;
import com.pic.share.service.utility.EmailConstructor;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class CounterScheduler {
	private final JavaMailSender javaMailSender;
	private final EmailConstructor emailConstructor;
	@Autowired private static IUserAppService userAppService;
	
	
	@Scheduled(cron = "0 0 0/1 * * *") /* second minute hour day month weekday */
	public void countUsersPosts() throws MessagingException {
		javaMailSender.send(emailConstructor.counterEmail());
	}

}
