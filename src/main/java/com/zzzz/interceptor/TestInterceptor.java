package com.zzzz.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 * 只能拦截Controller请求
 * 后于过滤器执行,执行顺序:过滤前->拦截前->Action处理->拦截后->过滤后
 * @author zhuangyilian
 * @date 2019年2月24日
 */
public class TestInterceptor implements HandlerInterceptor {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 在请求处理之前调用(Controller方法调用之前)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("TestInterceptor preHandle exec...");
		
		return true;
	}
	
	/**
	 * 在请求处理之后视图被渲染之前调用(Controller方法调用之后)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) 
			throws Exception {
		logger.info("TestInterceptor postHandle exec...");
	}
	
	/**
	 * 在整个请求结束之后调用(DispatcherServlet渲染了视图之后),主要是用于进行资源清理工作
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("TestInterceptor afterCompletion exec...");
	}
}
