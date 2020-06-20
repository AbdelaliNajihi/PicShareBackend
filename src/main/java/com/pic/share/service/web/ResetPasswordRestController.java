package com.pic.share.service.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pic.share.model.UserApp;
import com.pic.share.service.IUserAppService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ResetPasswordRestController {
	private final IUserAppService userAppService;
	
	@GetMapping(value = "/resetpassword/{email}")
	public ResponseEntity<?> resetPassword(@PathVariable(value = "email") String email) {
		UserApp user = userAppService.checkEmailExists(email);
		if(user == null) {
			return new ResponseEntity<String>("No account for this email, Please verify !", HttpStatus.BAD_REQUEST);
		}
		userAppService.resetPassword(user);
		return new ResponseEntity<String>("Email Sent...", HttpStatus.OK);
	}
	
}
