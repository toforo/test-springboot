package com.zzzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzzz.service.TestRedisService;

/**
 * Redis测试
 * @author zhuangyilian
 * @date 2019年2月13日
 */
@RestController
@RequestMapping("/redis")
public class TestRedisController {
	
	@Autowired
	private TestRedisService testRedisService;
	

	/**
	 * just test
	 * @author zhuangyilian
	 * @date 2019年2月11日
	 * @return
	 */
	@RequestMapping("/test")
	public String test(){
		
		return "test";
	}
	
	/**
	 * 设置缓存
	 * @author zhuangyilian
	 * @date 2019年2月13日
	 * @param key
	 * @param value
	 * @return
	 */
	@RequestMapping("/set")
	public String set(@RequestParam(value="key") String key, @RequestParam(value="value") String value){
		testRedisService.set(key, value);
		
		return "done";
	}
	
	/**
	 * 获取缓存
	 * @author zhuangyilian
	 * @date 2019年2月13日
	 * @param key
	 * @return
	 */
	@RequestMapping("/get")
	public String get(@RequestParam(value="key") String key){
		
		return testRedisService.get(key);
	}
	
	/**
	 * 删除缓存
	 * @author zhuangyilian
	 * @date 2019年2月13日
	 * @param key
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="key") String key){
		
		return testRedisService.delete(key) ? "succ" : "fail";
	}
	
	/**
	 * 设置缓存过期时间
	 * @author zhuangyilian
	 * @date 2019年2月13日
	 * @param key
	 * @param seconds
	 * @return
	 */
	@RequestMapping("/expire")
	public String expire(@RequestParam(value="key") String key, @RequestParam(value="seconds") long seconds){
		testRedisService.expire(key, seconds);
		
		return "done";
	}
	
}
