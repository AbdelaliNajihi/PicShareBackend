package com.pic.share.service.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/templates")
@RequiredArgsConstructor
public class TemplateController {
	Logger logger = LoggerFactory.getLogger(TemplateController.class);
	
	@GetMapping(value = "/registration")
	public String viewRegistrationTemplate(Model model) {
		logger.info("viewRegistrationTemplate");
		return "registrationTemplate";
	}
	
	@GetMapping(value = "/counter")
	public String viewCounterTemplate(Model model) {
		logger.info("viewCounterTemplate");
		return "counterTemplate";
	}
}
