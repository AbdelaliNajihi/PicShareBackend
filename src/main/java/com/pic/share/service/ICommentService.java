package com.pic.share.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.pic.share.dto.CommentDto;
import com.pic.share.model.Comment;

public interface ICommentService {
	public void saveComment(CommentDto dto, Authentication auth);
	public List<Comment> getAllComments();
	
}
