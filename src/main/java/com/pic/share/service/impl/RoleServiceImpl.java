package com.pic.share.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pic.share.dao.IRoleRepository;
import com.pic.share.exception.DuplicateException;
import com.pic.share.exception.NotFoundException;
import com.pic.share.model.Role;
import com.pic.share.service.IRoleService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
	private final IRoleRepository roleRepository; 
	
	@Override
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}
	
	@Override
	public void saveRole(Role r) {
		try {
			roleRepository.save(r);
		} catch (Exception e) {
			throw new DuplicateException("The role with the name : "+r.getName()+" already exists !");
		}
	}

	@Override
	public void deleteRole(Long roleId) {
		boolean exists = roleRepository.existsById(roleId);
		if (!exists) {
			throw new NotFoundException("The role with the ID : " +roleId+" does not exist !");
		}
		roleRepository.deleteById(roleId);
	}

}
