package com.zzzz.security;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro过滤配置
 * @author zhuangyilian
 * @date 2019年2月23日
 */
@Configuration
public class ShiroConfiguration {
	
	/**
	 * 注入自定义认证类
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@Bean
	public ShiroRealm shiroRealm(){
		
		return new ShiroRealm();
	}

	/**
	 * 注入安全管理类
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		//设置自定义认证类
		defaultWebSecurityManager.setRealm(shiroRealm());
		
		return defaultWebSecurityManager;
	}
	
	/**
	 * shiro过滤器
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//设置安全管理类
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//无认证时登录跳转(如果不设置,默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login"映射)
		shiroFilterFactoryBean.setLoginUrl("/shiro/toLogin");
		//认证成功后跳转
		shiroFilterFactoryBean.setSuccessUrl("/shiro/index");
		//无授权时跳转(只能用于授权拦截,对于认证不通过的无效果)
		shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/error403");	//根本不能用,无授权时只会抛出AuthorizationException,需在全局异常处理类进行处理
        
//		//自定义过滤器
//		Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
//		//登出过滤器
//		LogoutFilter logoutFilter = new LogoutFilter();
//		logoutFilter.setRedirectUrl("/shiro/toLogin");
//		filters.put("logout", logoutFilter);
//		shiroFilterFactoryBean.setFilters(filters);
        
		//自定义过滤链(按顺序过滤,使用LinkedHashMap,不能使用HashMap)
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		//登出(shiro已实现了登出逻辑,默认跳转网站首页.可不设置,而是在Controller自己实现)
		//filterChainDefinitionMap.put("/shiro/logout","logout");
		//开放测试
		filterChainDefinitionMap.put("/shiro/test", "anon");
		//开放登录页面
		filterChainDefinitionMap.put("/shiro/toLogin", "anon");
		//开放登录
		filterChainDefinitionMap.put("/shiro/login", "anon");
		//认证拦截
		filterChainDefinitionMap.put("/shiro/**", "authc");
		//其余开放
		filterChainDefinitionMap.put("/**", "anon");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilterFactoryBean;
	}

	/**
	 * 开启shiro注解支持
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		
		return authorizationAttributeSourceAdvisor;
	}
    
	/**
	 * 管理shiro的bean生命周期
	 * @author zhuangyilian
	 * @date 2019年2月23日
	 * @return
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		
		return new LifecycleBeanPostProcessor();
	}

}
