package com.zzzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 * 登录
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		
		return "shiro/login";
	}
	
	/**
	 * 登出
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/logout")
	public String logout(){
		
		return "logout";
	}
	
}
