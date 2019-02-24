package com.zzzz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.zzzz.interceptor.TestInterceptor;

/**
 * 拦截器配置
 * @author zhuangyilian
 * @date 2019年2月24日
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//这里可以添加多个拦截器
		registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
		
		super.addInterceptors(registry);
	}
}
