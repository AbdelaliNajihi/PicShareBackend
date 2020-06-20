package com.pic.share.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pic.share.model.UserApp;

@Repository
public interface IUserAppRepository extends JpaRepository<UserApp, Long> {
	UserApp findByUsername(String username);
	UserApp findByEmail(String email);
}
