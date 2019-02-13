package com.zzzz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zzzz.model.Test;
import com.zzzz.service.TestService;

/**
 * Thymeleaf模板引擎测试
 * @author zhuangyilian
 * @date 2019年2月11日
 */
@Controller	//不能使用@RestController,否则只会返回字符串,解析不到视图
@RequestMapping("/thymeleaf")
public class TestThymeleafController {
	
	@Autowired
	private TestService testService;
	
	
	/**
	 * just test
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param mv
	 * @return
	 */
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView mv){
		mv.setViewName("testThymeleaf");
		mv.addObject("title","欢迎使用Thymeleaf!");
		
		return mv;
	}
	
	/**
	 * 列表测试
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @param model
	 * @return
	 */
	@RequestMapping("/testList")
	public String testList(Model model){
		List<Test> testList = testService.findAll();
		
		model.addAttribute("testList", testList);
		
		return "thymeleaf/testList";
	}
	
	/**
	 * 表单测试
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @return
	 */
	@RequestMapping("/testForm")
	public String testForm(){
		
		return "thymeleaf/testForm";
	}
	
}
