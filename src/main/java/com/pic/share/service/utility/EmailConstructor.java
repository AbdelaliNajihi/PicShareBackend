package com.pic.share.service.utility;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.pic.share.model.UserApp;
import com.pic.share.service.IPostService;
import com.pic.share.service.IUserAppService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailConstructor {
	private final TemplateEngine templateEngine;
	@Value("${emailSender}")
	private String emailSender;
	@Autowired private IUserAppService userAppService;
	private final IPostService postService;
	Context context = new Context();
	
	public MimeMessagePreparator registrationEmail(UserApp user) {
		context.setVariable("user", user);
		String registrationTemplate = templateEngine.process("registrationTemplate", context);
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
				mimeMessageHelper.setTo(user.getEmail());
				mimeMessageHelper.setSubject("Confirm Your Account");
				mimeMessageHelper.setFrom(emailSender);
				mimeMessageHelper.setText(registrationTemplate, true);
			}
		};
		return mimeMessagePreparator;
	}
	
	public MimeMessagePreparator counterEmail() {
		context.setVariable("users", userAppService.countUsers());
		context.setVariable("onlineUsers", userAppService.countLoggedinUsers());
		context.setVariable("posts", postService.countPosts());
		String counterTemplate = templateEngine.process("counterTemplate", context);
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
				mimeMessageHelper.setTo(emailSender);
				mimeMessageHelper.setSubject("Daily Statistics");
				mimeMessageHelper.setText(counterTemplate, true);
			}
		};
		return mimeMessagePreparator;
	}
	
	public MimeMessagePreparator resetPasswordEmail(UserApp user, String password) {
		context.setVariable("user", user);
		context.setVariable("password", password);
		String registrationTemplate = templateEngine.process("resetPasswordTemplate", context);
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
				mimeMessageHelper.setTo(user.getEmail());
				mimeMessageHelper.setSubject("Reset Your Password");
				mimeMessageHelper.setFrom(emailSender);
				mimeMessageHelper.setText(registrationTemplate, true);
			}
		};
		return mimeMessagePreparator;
	}
}
