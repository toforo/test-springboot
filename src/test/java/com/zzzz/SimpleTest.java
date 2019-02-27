package com.zzzz;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzzz.config.JunitConfigTest;

/**
 * 简单测试
 * @author zhuangyilian
 * @date 2019年2月26日
 */
public class SimpleTest extends BaseTest {
	
	@Autowired
	private JunitConfigTest junitConfigTest;
	
	@Before
	public void testBefore(){
		System.out.println("junit before...");
	}
	
	@After
	public void testAfter(){
		System.out.println("junit after...");
	}
	
	@Test
	public void test(){
		System.out.println("junit test...");
	}
	
	@Test
	public void test2(){
		System.out.println("junit test2: " + junitConfigTest.getTestValue());
	}
}
