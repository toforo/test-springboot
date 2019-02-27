package com.zzzz.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 实现CommandLineRunner的Component会在所有Bean初始化之后,SpringApplication.run()之前执行
 * 非常适合应用程序启动时进行一些数据初始化工作
 * @author zhuangyilian
 * @date 2019年2月26日
 */
@Component
//@Order(1)	//设定执行顺序
public class TestInitRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("The Runner start to initialize...");
	}
}
