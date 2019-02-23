package com.zzzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzzz.dao.PermissionDao;
import com.zzzz.dao.RoleDao;
import com.zzzz.dao.UserDao;
import com.zzzz.model.Permission;
import com.zzzz.model.Role;
import com.zzzz.model.User;

@Service
public class TestShiroService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PermissionDao permissionDao;
	
	
	public User findUserByName(String userName){
		
		return userDao.findByName(userName);
	}
	
	public User findUserByNameAndPassword(String userName, String password){
		
		return userDao.findByNameAndPassword(userName, password);
	}
	
    public User addUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        
        return userDao.save(user);
    }

    public Role addRole(long userId, String roleName) {
        User user = userDao.getOne(userId);
        
        Role role = new Role();
        role.setName(roleName);
        role.setUser(user);
        
        return roleDao.save(role);
    }
    
    public Permission addPermission(long roleId, String permissionName) {
    	Role role = roleDao.getOne(roleId);
    	
    	Permission permission = new Permission();
    	permission.setName(permissionName);
    	permission.setRole(role);
    	
    	return permissionDao.save(permission);
    }
	
}
