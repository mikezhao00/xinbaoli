package com.george.xinbaoli.configuration;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * <PRE>
 * Filename:    BaseDataSource.java
 * Description: 配置数据源DruidDataSource
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年11月7日 下午3:02:19
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年11月7日      GONGZHAO     1.0         新建
 * </PRE>
 */
@Configuration
@EnableTransactionManagement
//mybatis支持
@MapperScan("com.george.xinbaoli.mapper")
public class BaseDataSource {

	private static final Logger log = LoggerFactory.getLogger(BaseDataSource.class);
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		log.debug("Cnfiguring DataSource");
		return new DruidDataSource();
	}
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	} 
}
