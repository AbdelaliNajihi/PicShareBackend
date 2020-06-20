package com.pic.share.service.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pic.share.model.UserApp;
import com.pic.share.service.IUserAppService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/register")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class RegisterRestController {
	private final Logger logger = LoggerFactory.getLogger(RegisterRestController.class);
	private final IUserAppService userAppService;

	@PostMapping(value = "")
	public ResponseEntity<?> addUser(UserApp u, MultipartFile file) throws IllegalStateException, IOException {
		logger.info("Enter at addUser");
		if (userAppService.checkUsernameExists(u.getUsername()) != null) {
			return new ResponseEntity<String>("usernameExists", HttpStatus.CONFLICT);
		}
		if (userAppService.checkEmailExists(u.getEmail()) != null) {
			return new ResponseEntity<String>("emailExists", HttpStatus.CONFLICT);
		}
		if(file == null) {
			return new ResponseEntity<String>("Please select a file !", HttpStatus.CONFLICT);
		}
		userAppService.saveUser(u, file);
		logger.info("The User "+u.getUsername()+" has registered...");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
