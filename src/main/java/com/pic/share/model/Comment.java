package com.pic.share.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Comment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	private String content;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime commentDate;
	@ManyToOne()
	@JoinColumn(name = "postId")
	@JsonIgnore
	private Post post;
	@ManyToOne()
	@JoinColumn(name = "userId")
	private UserApp userApp;
}
