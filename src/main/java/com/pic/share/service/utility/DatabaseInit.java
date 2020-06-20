package com.pic.share.service.utility;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pic.share.dao.IRoleRepository;
import com.pic.share.dao.IUserAppRepository;
import com.pic.share.model.Role;
import com.pic.share.model.UserApp;
import com.pic.share.service.IUserAppService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseInit {
	private final Logger logger = LoggerFactory.getLogger(DatabaseInit.class);
	private final IUserAppRepository userAppRepository;
	private final IRoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final IUserAppService userAppService;
	
	/*
	 * @PostConstruct
	 * 
	 * @Order(value = 1) private void createDefaultUser() { UserApp admin = new
	 * UserApp("admin", "admin@email.com", passwordEncoder.encode("password"),
	 * "male", LocalDate.of(1010, 10, 10), "biography", "pictureName.png");
	 * userAppRepository.save(admin); logger.info("ADMIN saved ..."); }
	 * 
	 * @PostConstruct
	 * 
	 * @Order(value = 2) private void createRoles() { List<Role> roles =
	 * Arrays.asList(new Role("ADMIN"), new Role("USER"));
	 * roleRepository.saveAll(roles); }
	 * 
	 * @PostConstruct
	 * 
	 * @Order(value = 3) private void addRoleAdmin() {
	 * userAppService.addRoleToUser(1L, "ADMIN"); }
	 */

}
