package com.pic.share.service.web;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pic.share.model.Post;
import com.pic.share.service.IPostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PostRestController {
	private final IPostService postService; 
	
	@GetMapping(value = "")
	public ResponseEntity<?> getPosts(@RequestParam(name = "key", defaultValue = "") String key, 
								@RequestParam(name = "page", defaultValue = "0") int page) {
		Page<Post> posts = postService.getPosts(key, PageRequest.of(page, 4));
		return ResponseEntity.ok(posts);
	}
	
	@PostMapping(value = "/new")
	public ResponseEntity<?> addPost(Post p, @RequestParam(name="file") MultipartFile file,
									Authentication auth) throws IllegalStateException, IOException{
		postService.savePost(p, file, auth);
		return new ResponseEntity<>("Post Successfully Created", HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable(value = "postId") Long postId){
		postService.deletePost(postId);
		return new ResponseEntity<>("Post Deleted", HttpStatus.OK);
	}
	
	@GetMapping(value = "/count")
	public ResponseEntity<?> countPosts(){
		long counter = postService.countPosts();
		return ResponseEntity.ok(counter);
	}
}
