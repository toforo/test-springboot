package com.zzzz.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理类(实现SpringMVC的HandlerExceptionResolver)
 * 需在启动类中进行注入
 * 只能处理请求过程中抛出的异常,不能处理异常处理本身抛出的异常和视图解析过程中抛出的异常
 * 没有进行异常处理时,BasicErrorController会继续处理(BasicErrorController会捕获/error的所有错误,过滤器中的错误会被重定向到/error)
 * @author zhuangyilian
 * @date 2019年2月23日
 */
@Component //在启动类中使用@Bean注册时,不用加@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		System.out.println("全局异常处理捕获了异常：" + ex.getMessage());
		
		//shiro授权拦截
		if (ex instanceof UnauthorizedException) {
			ModelAndView mv = new ModelAndView("shiro/error403");
			
			return mv;
		}
		
		//没有定义跳转的错误页面时,会跳转到BasicErrorController中定义的错误页面,即静态资源路径下error目录下的页面
//		ModelAndView mv = new ModelAndView("error500");
//
//		return mv;

		return null;
	}

}
