package com.zzzz.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zzzz.filter.LogFilter;

/**
 * 过滤器配置
 * @author zhuangyilian
 * @date 2019年2月24日
 */
@Configuration	//相当于xml的beans
public class FilterConfig {

	/**
	 * 注册过滤器
	 * @author zhuangyilian
	 * @date 2019年2月24日
	 * @return
	 */
	@Bean	//相当于xml的bean
	public FilterRegistrationBean<LogFilter> registFilter() {
		FilterRegistrationBean<LogFilter> registration = new FilterRegistrationBean<LogFilter>();
		registration.setFilter(new LogFilter());	//设置过滤器
		registration.addUrlPatterns("/*");	//过滤路径
		registration.setName("LogFilter");	//过滤器名
		registration.setOrder(1);	//过滤器执行顺序
		
		return registration;
	}
}
