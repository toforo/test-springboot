package com.zzzz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zzzz.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{

	User findByName(String userName);
	
	User findByNameAndPassword(String userName, String password);
	
}
