package com.pic.share.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pic.share.model.Comment;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

}
