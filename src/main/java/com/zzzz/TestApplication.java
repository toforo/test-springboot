package com.zzzz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * SpringBoot启动类
 * @author zhuangyilian
 * @date 2019年2月11日
 */
@SpringBootApplication
@MapperScan("com.zzzz.mapper")	//Mybatis的Mapper包扫描,不使用时需在Mapper类上加@Mapper
@EnableCaching	//开启缓存
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
