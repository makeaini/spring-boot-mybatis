package com.spring.boot.mybatis.config;

import java.sql.SQLException;
import java.util.Properties;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.aopalliance.aop.Advice;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.spring.boot.mybatis.mapper.master",sqlSessionFactoryRef = "masterSqlSessionFactory")
@EnableTransactionManagement
public class MasterDataSourceConfig implements EnvironmentAware{
	private Environment env;
	@Autowired
	private PageInterceptor pageInterceptor;
	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}


	@Bean(initMethod = "init", destroyMethod = "close",name = "masterDataSource")
	@Primary
	public DruidDataSource masterDataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(env.getProperty("master.datasource.driver-class-name").trim());
		dataSource.setUrl(env.getProperty("master.datasource.url").trim());
		dataSource.setUsername(env.getProperty("master.datasource.username").trim());
		dataSource.setPassword(env.getProperty("master.datasource.password").trim());
		dataSource.setFilters(env.getProperty("datasource.filters").trim());
		dataSource.setMaxActive(Integer.parseInt(env.getProperty("datasource.maxActive").trim()));
		dataSource.setInitialSize(Integer.parseInt(env.getProperty("datasource.initialSize").trim()));
		dataSource.setMaxWait(Long.parseLong(env.getProperty("datasource.maxWait").trim()));
		dataSource.setMinIdle(Integer.parseInt(env.getProperty("datasource.minIdle").trim()));
		dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("datasource.timeBetweenEvictionRunsMillis").trim()));
		dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("datasource.minEvictableIdleTimeMillis").trim()));
		dataSource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("datasource.testOnBorrow").trim()));
		dataSource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("datasource.testOnReturn").trim()));
		dataSource.setValidationQuery(env.getProperty("datasource.validationQuery").trim());
		Properties properties = new Properties();
		properties.setProperty("druid.stat.slowSqlMillis","5000");
		dataSource.setConnectProperties(properties);
		return dataSource;
	}
	@Bean(name = "masterTransactionManager")
	@Primary
	public DataSourceTransactionManager masterTransactionManager() throws SQLException {
		return new DataSourceTransactionManager(masterDataSource());
	}
	@Bean(name = "masterSqlSessionFactory")
	@Primary
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(masterDataSource());
		Interceptor[] plugins = new Interceptor[] { pageInterceptor };
		sqlSessionFactoryBean.setPlugins(plugins);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.spring.boot.mybatis.entity");
		return sqlSessionFactoryBean.getObject();
	}

}
