package com.pic.share.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pic.share.dao.ICommentRepository;
import com.pic.share.dao.IPostRepository;
import com.pic.share.dao.IUserAppRepository;
import com.pic.share.dto.CommentDto;
import com.pic.share.exception.NotFoundException;
import com.pic.share.mapper.CommentMapper;
import com.pic.share.model.Comment;
import com.pic.share.model.Post;
import com.pic.share.model.UserApp;
import com.pic.share.service.ICommentService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {
	private final ICommentRepository commentRepository;
	private final IPostRepository postRepository; 
	private final CommentMapper commentMapper;
	private final IUserAppRepository userAppRepository;
	
	@Override
	public void saveComment(CommentDto dto, Authentication auth) {
		try {
			UserApp user = userAppRepository.findByUsername(auth.getName());
			Post post = postRepository.getOne(dto.getPostId());
			if (post == null) {
				throw new NotFoundException("The Post With The ID : "+dto.getPostId()+" Does Not Exist !");
			}
			Comment comment = commentMapper.commentDtoToComment(dto);
			comment.setCommentDate(LocalDateTime.now());
			comment.setUserApp(user);
			post.getComments().add(comment);
			commentRepository.save(comment);
		} catch (Exception e) {
			throw new NotFoundException("The Post With The ID : "+dto.getPostId()+" Does Not Exist !");
		}
	}

	@Override
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

}
