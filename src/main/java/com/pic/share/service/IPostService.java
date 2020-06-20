package com.pic.share.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.pic.share.model.Post;

public interface IPostService {
	public void savePost(Post p, MultipartFile file, Authentication auth) throws IllegalStateException, IOException;
	public Page<Post> getPosts(String key, Pageable pageable);
	public long countPosts();
	public void deletePost(Long postId);
}
