package com.pic.share.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.pic.share.model.Post;
import com.pic.share.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDto implements Serializable {
	private Long userId;
	private String username;
	private String email;
	private String gender;
	private LocalDate birthDate;
	private String biography;
	private boolean isDeleted;
	private Set<Role> roles;
	private List<Post> posts;
	private List<Post> likedPosts;
	private List<Post> dislikedPosts;
}
