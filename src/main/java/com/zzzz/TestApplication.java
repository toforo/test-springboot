package com.zzzz;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
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
//@EnableScheduling	//开启定时任务
public class TestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.out.println("The service will start...");
		
		SpringApplication.run(TestApplication.class, args);
		
		System.out.println("The service has started...");
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources(TestApplication.class);
	}
	
	/**
	 * 全局异常处理类
	 * 类上加了@Component时,不用该方法
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
//	@Bean	//以方法名为id生产并注册bean(用于返回对象的方法上,与@Component作用一样,但是更灵活)
//	public GlobalExceptionResolver globalExceptionResolver() {
//		
//		return new GlobalExceptionResolver();
//	}
	
	/**
	 * 配置mybatis的分页插件pageHelper
	 * @author zhuangyilian
	 * @date 2019年3月5日
	 * @return
	 */
	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum","true");
		properties.setProperty("rowBoundsWithCount","true");
		properties.setProperty("reasonable","true");
		properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}
    
}
