package com.pic.share.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.pic.share.dto.CommentDto;
import com.pic.share.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
	@Mappings({
		@Mapping(target = "post.postId", source = "postId")
	})
	Comment commentDtoToComment(CommentDto dto);
}
