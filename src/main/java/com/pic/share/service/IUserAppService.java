package com.pic.share.service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.pic.share.dto.UserDto;
import com.pic.share.model.UserApp;

public interface IUserAppService {
	public void saveUser(UserApp u, MultipartFile file) throws IllegalStateException, IOException;
	public UserDto getUser(Long userId);
	public UserDto getUserByName(String username);
	public List<UserDto> getAllUsers();
	public void deleteUser(Long userId);
	public void updateUser(UserApp u, Long userId, MultipartFile file) throws IllegalStateException, IOException;
	public void addRoleToUser(Long userId, String name);
	public void removeRoleFromUser(Long userId, String name);
	public void removeAllRolesFromUser(Long userId);
	public void likePost(Long postId, Authentication auth);
	public void dislikePost(Long postId, Authentication auth);
	public long countUsers();
	public UserDto getLoggedinUser(Authentication auth);
	public long countLoggedinUsers();
	public UserApp checkUsernameExists(String username);
	public UserApp checkEmailExists(String email);
	public void resetPassword(UserApp u);
}
