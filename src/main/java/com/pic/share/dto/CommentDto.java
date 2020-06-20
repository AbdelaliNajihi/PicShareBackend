package com.pic.share.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CommentDto implements Serializable {
	private Long commentId;
	private String content;
	private LocalDateTime commentDate;
	private Long postId;
}
