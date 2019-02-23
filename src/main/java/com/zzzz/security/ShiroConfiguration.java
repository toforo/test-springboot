package com.zzzz.security;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
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
        //登录跳转(如果不设置,默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login"映射)
        shiroFilterFactoryBean.setLoginUrl("/shiro/login");
        //登录成功后跳转
        shiroFilterFactoryBean.setSuccessUrl("/shiro/index");
        //认证不通过时跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/error403");
        
        //自定义过滤链(按顺序过滤)
        Map<String,String> filterChainDefinitionMap = new HashMap<String, String>();
        //登出(shiro已实现了登出逻辑)
        filterChainDefinitionMap.put("/shiro/logout","logout");
        //开放测试
        filterChainDefinitionMap.put("/shiro/test", "anon");
        //开放登录
        filterChainDefinitionMap.put("/shiro/login", "anon");
        //认证拦截
        filterChainDefinitionMap.put("/shiro/**", "authc");
        //其余开放
        filterChainDefinitionMap.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        
        return shiroFilterFactoryBean;
    }
	
}
