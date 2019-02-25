package com.zzzz;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zzzz.TestApplication;
import com.zzzz.config.JunitConfigTest;

/**
 * Junit测试
 * 注意src/test/java中不要注入src/main/java中的类,否则运行时会找不到类
 * @author zhuangyilian
 * @date 2019年2月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)	//指定测试运行器为SpringJUnit4ClassRunner,从而自动创建Spring运行环境
@SpringBootTest(classes=TestApplication.class)	//指定SpringBoot工程的Application启动类(会测试开始前启动SpringBoot项目)
@WebAppConfiguration	//对于Web项目,Junit需要模拟ServletContext,因此需要在测试类加上@WebAppConfiguration(如果不测试Web相关的可以不加)
public class ApplicationTest {
	
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
