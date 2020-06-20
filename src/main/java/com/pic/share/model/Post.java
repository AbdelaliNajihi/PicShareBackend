package com.pic.share.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
@ApiModel(description = "Description")
public class Post implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	private String title;
	private String postName;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime postedDate;
	private int likes;
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Comment> comments;
	@ManyToOne()
	@JoinColumn(name = "userId")
	private UserApp userApp;
}
