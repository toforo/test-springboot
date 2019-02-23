package com.zzzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzzz.dao.PermissionDao;
import com.zzzz.dao.RoleDao;
import com.zzzz.dao.UserDao;
import com.zzzz.model.User;

@Service
public class TestShiroService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PermissionDao permissionDao;
	
	
	public User findByName(String name){
		
		return userDao.findByName(name);
	}
	
}
