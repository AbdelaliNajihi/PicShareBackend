package com.pic.share.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pic.share.dao.IPostRepository;
import com.pic.share.dao.IUserAppRepository;
import com.pic.share.exception.NotFoundException;
import com.pic.share.model.Post;
import com.pic.share.model.UserApp;
import com.pic.share.service.IPostService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService {
	private final IPostRepository postRepository;
	private final IUserAppRepository userAppRepository;
	@Value("${posts}")
	private String posts;

	@Override
	public void savePost(Post p, MultipartFile file, Authentication auth) throws IllegalStateException, IOException {
		UserApp user = userAppRepository.findByUsername(auth.getName());
		p.setPostedDate(LocalDateTime.now());
		File postsDirectory = new File(posts);
		if (!postsDirectory.exists()) {
			postsDirectory.mkdirs();
		}
		if (!file.isEmpty()) {
			p.setPostName(file.getOriginalFilename());
			postRepository.save(p);
			file.transferTo(new File(posts + p.getPostId()));
		}
		p.setUserApp(user);
		postRepository.save(p);
	}

	@Override
	public Page<Post> getPosts(String key, Pageable pageable) {
		return postRepository.findByTitleContainingOrderByPostedDateDesc(key, pageable);
	}

	@Override
	public long countPosts() {
		return postRepository.count();
	}

	@Override
	public void deletePost(Long postId) {
		Post post = postRepository.getOne(postId);
		if (post == null) {
			throw new NotFoundException("The Post With This ID : " + postId + " Doesn't Exist");
		}
		postRepository.deleteById(postId);
	}
}
