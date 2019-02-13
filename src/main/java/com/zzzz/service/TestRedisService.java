package com.zzzz.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zzzz.dao.TestDao;

@Service
public class TestRedisService {
	
	@Autowired
	private TestDao testDao;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;	//必须设置redis连接属性
	
	
	/**
	 * 设置缓存
	 * @author zhuangyilian
	 * @date 2019年2月13日
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		
		redisTemplate.boundValueOps(key).set(value);
	}
	
	/**
	 * 获取缓存
	 * @author zhuangyilian
	 * @date 2019年2月13日
	 * @param key
	 * @return
	 */
	public String get(String key) {
		
		return redisTemplate.boundValueOps(key).get();
	}
	
	/**
	 * 删除缓存
	 * @author zhuangyilian
	 * @date 2019年2月13日
	 * @param key
	 * @return
	 */
	public boolean delete(String key) {
		
		return redisTemplate.delete(key);
	}
	
	/**
	 * 设置缓存过期时间
	 * @author zhuangyilian
	 * @date 2019年2月13日
	 * @param key
	 * @param seconds
	 */
	public void expire(String key, long seconds) {
		
		redisTemplate.boundValueOps(key).expire(seconds, TimeUnit.SECONDS);
	}
	
}
