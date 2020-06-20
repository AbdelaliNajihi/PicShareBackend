package com.pic.share.service.web;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pic.share.dto.UserDto;
import com.pic.share.model.Role;
import com.pic.share.model.UserApp;
import com.pic.share.service.IUserAppService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserAppRestController {
	private final IUserAppService userAppService;

	@GetMapping(value = "")
	public ResponseEntity<?> getAllUsers() {
		List<UserDto> users = userAppService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping(value = "/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable(value = "userId") Long userId) {
		UserDto user = userAppService.getUser(userId);
		return ResponseEntity.ok(user);
	}

	@GetMapping(value = "/username")
	public ResponseEntity<?> getUserByUsername(@RequestParam(name = "username") String username) {
		UserDto user = userAppService.getUserByName(username);
		return ResponseEntity.ok(user);
	}

	@PostMapping(value = "/like/{postId}")
	public ResponseEntity<?> likePost(@PathVariable(value = "postId") Long postId, Authentication auth) {
		userAppService.likePost(postId, auth);
		return new ResponseEntity<>("Post Successfully Liked", HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/dislike/{postId}")
	public ResponseEntity<?> dislikePost(@PathVariable(value = "postId") Long postId, Authentication auth) {
		userAppService.dislikePost(postId, auth);
		return new ResponseEntity<>("Post Successfully Liked", HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/loggedin")
	public ResponseEntity<?> getLoggedinUser(Authentication auth) {
		UserDto user = userAppService.getLoggedinUser(auth);
		return ResponseEntity.ok(user);
	}

	@PostMapping(value = "/addRole/{userId}")
	public ResponseEntity<?> addRoleToUser(@PathVariable(value = "userId") Long userId, @RequestBody Role role) {
		userAppService.addRoleToUser(userId, role.getName());
		return new ResponseEntity<>("Role Successfuly Added To The User", HttpStatus.OK);
	}

	@PostMapping(value = "/removeRole/{userId}")
	public ResponseEntity<?> removeRoleFromUser(@PathVariable(value = "userId") Long userId, @RequestBody Role role) {
		userAppService.removeRoleFromUser(userId, role.getName());
		return new ResponseEntity<>("Role Successfuly Added To The User", HttpStatus.OK);
	}

	@PutMapping(value = "/update/{userId}")
	public ResponseEntity<?> updateUser(UserApp u, @PathVariable(value = "userId") Long userId, MultipartFile file)
			throws IllegalStateException, IOException {
		userAppService.updateUser(u, userId, file);
		return new ResponseEntity<>("User Updated", HttpStatus.OK);
	}

	@PutMapping(value = "/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") Long userId) {
		userAppService.deleteUser(userId);
		return new ResponseEntity<>("User Updated", HttpStatus.OK);
	}
	
	@GetMapping(value = "/count")
	public ResponseEntity<?> countUsers() {
		long counter = userAppService.countUsers();
		return ResponseEntity.ok(counter);
	}

	@GetMapping(value = "/loggedin/count")
	public ResponseEntity<?> countLoggedinUsers() {
		long counter = userAppService.countLoggedinUsers();
		return ResponseEntity.ok(counter);
	}
}
