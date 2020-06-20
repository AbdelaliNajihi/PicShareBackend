package com.pic.share.service.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pic.share.model.Role;
import com.pic.share.service.IRoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/roles")
@CrossOrigin(origins = "http://localhost:4200") 
@RequiredArgsConstructor
public class RoleRestController {
	private final IRoleService roleService;
	
	@GetMapping(value = "")
	public ResponseEntity<?> getAllRoles(){
		List<Role> roles = roleService.getRoles();
		return ResponseEntity.ok(roles);
	}
}
