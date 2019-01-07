package com.george.xinbaoli.configuration;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.george.xinbaoli.common.MyRealm;

/**
 * <PRE>
 * Filename:    ShiroConfiguration.java
 * Description: shiro配置组件
 * </PRE>
 * 
 * @author GONGZHAO
 * @version 1.0
 * 
 *          <PRE>
 * Create at:   2018年11月7日 下午3:22:29
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年11月7日      GONGZHAO     1.0         新建
 *          </PRE>
 */
@Configuration
public class ShiroConfiguration {
	private static final Logger log = LoggerFactory.getLogger(ShiroConfiguration.class);

	/**
	 * 
	 * @Description: 注入Realm
	 * @author GONGZHAO
	 * @Created 2018年11月7日下午3:36:26
	 * @since 1.0
	 * @return
	 */
	@Bean(name = "myRealm")
	public MyRealm myAuthRealm() {
		MyRealm myRealm = new MyRealm();
		log.info("myRealm注册完成");
		return myRealm;
	}

	/**
	 * 
	 * @Description: 注入securityManager
	 * @author GONGZHAO
	 * @Created 2018年11月7日下午3:44:25
	 * @since 1.0
	 * @param myRealm
	 * @return
	 */
	@Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("myRealm") MyRealm myRealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(myRealm);
		log.info("securityManager注册完成");
		return manager;
	}

	/**
	 * 
	 * @Description: 注入filter
	 * @author GONGZHAO 
	 * @Created 2018年11月7日下午3:56:00
	 * @since 1.0
	 * @param securityManager
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
		ShiroFilterFactoryBean filterFatoryBean = new ShiroFilterFactoryBean();
		filterFatoryBean.setSecurityManager(securityManager);
		// 配置登录的url和登录成功后的url
		filterFatoryBean.setLoginUrl("/auth/login");
		filterFatoryBean.setSuccessUrl("/home");
		// 配置未授权跳转页面
		filterFatoryBean.setUnauthorizedUrl("errorPage/403");
		// 配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/css/**", "anon");// 表示可以匿名访问
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/auth/**", "anon");
		filterChainDefinitionMap.put("/errorPage/**", "anon");
		filterChainDefinitionMap.put("/demo/**", "anon");
		filterChainDefinitionMap.put("/swagger-*/**", "anon");
//		filterChainDefinitionMap.put("**/swagger-resources/**", "anon");

		filterChainDefinitionMap.put("/swagger-ui.html/**", "anon");
		filterChainDefinitionMap.put("/webjars/**", "anon");
		filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");// 表示admin权限才可以访问
        filterChainDefinitionMap.put("/*", "authc");// 表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/*.*", "authc");
        filterFatoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        log.info("shiroFilter注册完成");
        return filterFatoryBean;
	}
}
