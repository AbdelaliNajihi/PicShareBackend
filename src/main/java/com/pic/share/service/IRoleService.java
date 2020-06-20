package com.pic.share.service;

import java.util.List;

import com.pic.share.model.Role;

public interface IRoleService {
	public List<Role> getRoles();
	public void saveRole(Role r);
	public void deleteRole(Long roleId);
}
