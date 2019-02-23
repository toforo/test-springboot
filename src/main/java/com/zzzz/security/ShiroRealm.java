package com.zzzz.security;

import java.util.HashSet;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzzz.model.Permission;
import com.zzzz.model.Role;
import com.zzzz.model.User;
import com.zzzz.service.TestShiroService;

/**
 * 自定义Shiro认证类,负责用户认证和权限处理
 * @author zhuangyilian
 * @date 2019年2月23日
 */
public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private TestShiroService testShiroService; 

	
	/**
	 * 授权(授权角色和权限)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//登录用户名
		String userName = principalCollection.getPrimaryPrincipal().toString();
		//查询用户
		User user = testShiroService.findUserByName(userName);
		
		//添加角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		for (Role role : user.getRoles()) {
			//添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            for (Permission permission : role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getName());
            }
		}
		
		//返回授权信息
		return simpleAuthorizationInfo;
	}

	/**
	 * 认证(验证用户名和密码)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//		Object principal = authenticationToken.getPrincipal();
//		Object credentials = authenticationToken.getCredentials();
//		//post请求会先进认证,再到请求
//		if(principal == null || credentials == null){
//			return null;
//		}
//		
//		//用户名
//		String userName = principal.toString();
//		//密码
//		String password = authenticationToken.getCredentials().toString();
		
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
		
		//用户名
		String userName = usernamePasswordToken.getUsername();
		//密码
		String password = new String(usernamePasswordToken.getPassword());
		
		//查询用户
		User user = testShiroService.findUserByNameAndPassword(userName, password);
//		User user = testShiroService.findUserByName(userName);	//也可以直接根据用户名查询,因为Shiro会自动进行密码校验
		if(user == null){
			throw new AccountException("用户名或密码错误");
		}
		
		//认证成功,返回认证信息 
        return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), this.getClass().getName());
	}

}
