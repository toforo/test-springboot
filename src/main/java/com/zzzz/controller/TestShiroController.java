package com.zzzz.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzzz.model.Permission;
import com.zzzz.model.Role;
import com.zzzz.model.User;
import com.zzzz.service.TestShiroService;

/**
 * Shiro测试
 * @author zhuangyilian
 * @date 2019年2月23日
 */
@Controller
@RequestMapping("/shiro")
public class TestShiroController {
	
	@Autowired
	private TestShiroService testShiroService;
	

	/**
	 * just test
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/test")
	public String test(){
		
		return "test";
	}
	
	/**
	 * 首页
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@RequestMapping("/index")
	public String index(){
		
		return "shiro/index";
	}
	
	/**
	 * 登录页面
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@RequestMapping(value="/toLogin", method = RequestMethod.GET)
	public String toLogin(){
		
		return "shiro/toLogin";
	}
	
	/**
	 * 登录
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(User user, HttpSession session){
		//用户名
		String userName = user.getName();
		//密码
		String password = user.getPassword();
		
		//获取用户主题
		Subject subject = SecurityUtils.getSubject();
		//创建登录令牌
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
        
        try {
        	//登录认证
        	subject.login(usernamePasswordToken);
        	
        	//查询用户
        	user = testShiroService.findUserByName(userName);
            //将用户信息存入session
            session.setAttribute("user", user);
        	
            return "shiro/index";
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
        
        return "shiro/toLogin";
	}
	
	/**
	 * 登出
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		//获取用户主题
		Subject subject = SecurityUtils.getSubject();
		//登出
		subject.logout();
		
		//shiro的logout()会自动清除session数据
//		session.removeAttribute("user");
		
		return "/shiro/toLogin";
	}
	
	/**
	 * 添加用户
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
//	@RequiresRoles("user")	//角色,无角色时抛出AuthorizationException
    @RequiresPermissions("user:add")	//权限,无权限时抛出AuthorizationException
	@ResponseBody
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	public String addUesr(User user){
		user = testShiroService.addUser(user.getName(), user.getPassword());
		
		return "shiro/addUesr: " + user;
	}
	
	/**
	 * 添加角色
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addRole", method = RequestMethod.POST)
	public String addRole(@RequestParam(value="userId", defaultValue="0") long userId, @RequestParam(value="roleName") String roleName){
		Role role = testShiroService.addRole(userId, roleName);
		
		return "shiro/addRole: " + role;
	}
	
	/**
	 * 添加权限
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addPermission", method = RequestMethod.POST)
	public String addPermission(@RequestParam(value="roleId", defaultValue="0") long roleId, @RequestParam(value="permissionName") String permissionName){
		Permission permission = testShiroService.addPermission(roleId, permissionName);
		
		return "shiro/addPermission: " + permission;
	}

}
