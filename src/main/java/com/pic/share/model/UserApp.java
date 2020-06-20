package com.pic.share.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER")
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserApp implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String email;
	private String password;
	private String gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	private String biography;
	private String pictureName;
	private boolean isDeleted;
	@OneToMany(mappedBy = "userApp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Post> posts;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "LikedPosts", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "postId", referencedColumnName = "postId"))
	@JsonIgnore
	private List<Post> likedPosts;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DislikedPosts", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "postId", referencedColumnName = "postId"))
	@JsonIgnore
	private List<Post> dislikedPosts;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "UserRoles", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "roleId"))
	@JsonIgnore
	private Set<Role> roles = new HashSet<>();
	@OneToMany(mappedBy = "userApp")
	@JsonIgnore
	private List<Comment> comments;
	
	public UserApp(String username, String email, String password, String gender, LocalDate birthDate, String biography,
			String pictureName) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.birthDate = birthDate;
		this.biography = biography;
		this.pictureName = pictureName;
	}
		
}
