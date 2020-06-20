package com.pic.share.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pic.share.model.Post;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
	Page<Post> findByTitleContainingOrderByPostedDateDesc(String key, Pageable pageable);
}
