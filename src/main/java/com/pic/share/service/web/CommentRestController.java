package com.pic.share.service.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pic.share.dto.CommentDto;
import com.pic.share.model.Comment;
import com.pic.share.service.ICommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/comments")
@CrossOrigin(value = "http://localhost:4200")
@RequiredArgsConstructor
public class CommentRestController {
	private final ICommentService commentService;
	
	@GetMapping(value = "")
	public ResponseEntity<?> getAllComments(){
		List<Comment> comments = commentService.getAllComments();
		return ResponseEntity.ok(comments);
	}
	
	@PostMapping(value = "/new")
	public ResponseEntity<?> addComment(@RequestBody CommentDto dto, Authentication auth) {
		commentService.saveComment(dto, auth);
		return new ResponseEntity<>("Comment Successfuly Created", HttpStatus.CREATED);
	}
}
