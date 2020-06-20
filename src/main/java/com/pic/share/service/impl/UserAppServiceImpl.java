package com.pic.share.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pic.share.dao.IPostRepository;
import com.pic.share.dao.IRoleRepository;
import com.pic.share.dao.IUserAppRepository;
import com.pic.share.dto.UserDto;
import com.pic.share.exception.NotFoundException;
import com.pic.share.mapper.UserMapper;
import com.pic.share.model.Post;
import com.pic.share.model.Role;
import com.pic.share.model.UserApp;
import com.pic.share.service.IUserAppService;
import com.pic.share.service.utility.EmailConstructor;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAppServiceImpl implements IUserAppService {
	
	private final Logger logger = LoggerFactory.getLogger(UserAppServiceImpl.class);
	
	private final IUserAppRepository userRepository;
	private final IRoleRepository roleRepository;
	private final IPostRepository postRepository;
	private final JavaMailSender javaMailSender;
	private final UserMapper userMapper;
	private final SessionRegistry sessionRegistry;
	@Value("${users}")
	private String users;
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private EmailConstructor emailConstructor;

	@Override
	public void saveUser(UserApp u, MultipartFile file) throws IllegalStateException, IOException {
			u.setPassword(passwordEncoder.encode(u.getPassword()));
			File usersDirectory = new File(users);
			if (!usersDirectory.exists()) {
				usersDirectory.mkdirs();
			}
			if (file != null && !file.isEmpty()) {  // NPE
				u.setPictureName(file.getOriginalFilename());
				userRepository.save(u);
				file.transferTo(new File(users + u.getUserId()));
			}
			addRoleToUser(u.getUserId(), "USER");
			javaMailSender.send(emailConstructor.registrationEmail(u));
			userRepository.save(u);
	}

	@Override
	public UserDto getUser(Long userId) {
		boolean exists = userRepository.existsById(userId);
		if (!exists) {
			throw new NotFoundException("The User With The ID : +" + userId + " Does Not Exist !");
		}
		return userMapper.userToUserDto(userRepository.getOne(userId));
	}

	@Override
	public UserDto getUserByName(String username) {
		UserApp user = userRepository.findByUsername(username);
		return userMapper.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userMapper.usersToUsersDto(userRepository.findAll());
	}

	@Override
	public void deleteUser(Long userId) {
		UserApp user = userRepository.getOne(userId);
		if (user == null) {
			throw new NotFoundException("The User With The ID : +" + userId + " Does Not Exist !");
		}
		user.setUsername("UNKNOW USER");
		user.setDeleted(true);
		userRepository.save(user);
	}

	@Override
	public void updateUser(UserApp u, Long userId, MultipartFile file) throws IllegalStateException, IOException {
		UserApp user = userRepository.getOne(userId);
		user.setUsername(u.getUsername());
		user.setEmail(u.getEmail());
		user.setGender(u.getGender());
		user.setBirthDate(u.getBirthDate());
		user.setBiography(u.getBiography());
		if (file != null && !file.isEmpty()) {  // NPE
			user.setPictureName(file.getOriginalFilename());
			userRepository.save(user);
			Files.write(Paths.get(users+user.getUserId()), file.getBytes());
		}
		userRepository.save(user);
	}

	@Override
	public void addRoleToUser(Long userId, String name) {
		UserApp user = userRepository.getOne(userId);
		Role role = roleRepository.findByName(name);
		if (role == null) {
			throw new NotFoundException("The Role With The Name : " + name + " Does Not Exist !");
		}
		user.getRoles().add(role);
		userRepository.save(user);
	}

	@Override
	public void removeRoleFromUser(Long userId, String name) {
		UserApp user = userRepository.getOne(userId);
		Role role = roleRepository.findByName(name);
		if (role == null) {
			throw new NotFoundException("The Role With The ID : +" + name + " Does Not Exist !");
		}
		user.getRoles().remove(role);
		userRepository.save(user);
	}

	@Override
	public void removeAllRolesFromUser(Long userId) {
		UserApp user = userRepository.getOne(userId);
		user.getRoles().clear();
		userRepository.save(user);
	}

	@Override
	public void likePost(Long postId, Authentication auth) {
		UserApp user = userRepository.findByUsername(auth.getName());
		Post post = postRepository.getOne(postId);
		if (post == null) {
			throw new NotFoundException("The Role With The ID : +" + postId + " Does Not Exist !");
		}
		post.setLikes(1);
		if(user.getLikedPosts().contains(post)) {
			return;
		}else
		user.getLikedPosts().add(post);
		userRepository.save(user);
	}

	@Override
	public void dislikePost(Long postId, Authentication auth) {
		UserApp user = userRepository.findByUsername(auth.getName());
		Post post = postRepository.getOne(postId);
		if (post == null) {
			throw new NotFoundException("The Role With The ID : +" + postId + " Does Not Exist !");
		}
		if (post.getLikes() > 0) {
			post.setLikes(post.getLikes() - 1);
		} else
			post.setLikes(0);
		if(user.getDislikedPosts().contains(post)) {
			return;
		}else
		user.getDislikedPosts().add(post);
		userRepository.save(user);
	}

	@Override
	public long countUsers() {
		return userRepository.count();
	}

	@Override
	public UserDto getLoggedinUser(Authentication auth) {
		UserApp user = userRepository.findByUsername(auth.getName());
		return userMapper.userToUserDto(user);
	}

	@Override
	public long countLoggedinUsers() {
		return sessionRegistry.getAllPrincipals().size();
	}

	@Override
	public UserApp checkUsernameExists(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserApp checkEmailExists(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void resetPassword(UserApp u) {
		String password = RandomString.make(6);
		String encryptedPassword = passwordEncoder.encode(password);
		u.setPassword(encryptedPassword);
		userRepository.save(u);
		javaMailSender.send(emailConstructor.resetPasswordEmail(u, password));
	}

}
