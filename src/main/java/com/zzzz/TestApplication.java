package com.zzzz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.zzzz.exception.GlobalExceptionResolver;

/**
 * SpringBoot启动类
 * 使用外部tomcat时,必须继承SpringBootServletInitializer,并实现configure
 * 外部tomcat版本最好与springboot内置tomcat版本一致,否则可能导致服务启动不了
 * @author zhuangyilian
 * @date 2019年2月11日
 */
@SpringBootApplication
@MapperScan("com.zzzz.mapper")	//Mybatis的Mapper包扫描,不使用时需在Mapper类上加@Mapper
@EnableCaching	//开启缓存
public class TestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		
		SpringApplication.run(TestApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources(TestApplication.class);
	}
	
	/**
	 * 注入全局异常处理类
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@Bean
	public GlobalExceptionResolver globalExceptionResolver() {
		
		return new GlobalExceptionResolver();
	}
    
}
