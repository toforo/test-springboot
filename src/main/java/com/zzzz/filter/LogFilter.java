package com.zzzz.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义过滤器(日志过滤器)
 * 过滤器可以过滤几乎所有请求
 * @author zhuangyilian
 * @date 2019年2月24日
 */
public class LogFilter implements Filter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		//初始化Filter时调用
	};
	
	@Override
	public void destroy() {
		
		//销毁Filter时调用
	};

	/**
	 * 过滤处理
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		logger.info("请求地址: {}", httpServletRequest.getRequestURL().toString());
		
		chain.doFilter(request,response);
	}
}
