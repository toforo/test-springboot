package com.zzzz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zzzz.interceptor.TestInterceptor;

/**
 * 拦截器配置
 * 最好实现WebMvcConfigurer,而不要继承WebMvcConfigurationSupport
 * 继承WebMvcConfigurationSupport,会导致springboot自动配置失效(会导致访问不到默认的静态资源路径)
 * @author zhuangyilian
 * @date 2019年2月24日
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//可以添加多个拦截器
		InterceptorRegistration testInterceptor = registry.addInterceptor(new TestInterceptor());
		//排除配置
		testInterceptor.excludePathPatterns("/test");
		testInterceptor.excludePathPatterns("/test2/**");
		//拦截配置
		testInterceptor.addPathPatterns("/**");
	}
	
	/**
	 * 添加静态资源路径
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/testStatic/**").addResourceLocations("classpath:/testStatic/");
	}
}