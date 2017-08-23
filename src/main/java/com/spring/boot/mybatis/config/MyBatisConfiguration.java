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
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.spring.boot.mybatis.mapper")
@EnableTransactionManagement
public class MyBatisConfiguration implements EnvironmentAware{
	private Environment env;
	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}

//			# 打开PSCache，并且指定每个连接上PSCache的大小
//	spring.datasource.poolPreparedStatements=true
//	spring.datasource.maxPoolPreparedStatementPerConnectionSize=20
//			# 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
//	spring.datasource.filters=stat,wall,log4j
//# 通过connectProperties属性来打开mergeSql功能；慢SQL记录
//	spring.datasource.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
	// 注册dataSource
	@Bean(initMethod = "init", destroyMethod = "close")
	public DruidDataSource dataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name").trim());
		dataSource.setUrl(env.getProperty("spring.datasource.url").trim());
		dataSource.setUsername(env.getProperty("spring.datasource.username").trim());
		dataSource.setPassword(env.getProperty("spring.datasource.password").trim());
		dataSource.setFilters(env.getProperty("spring.datasource.filters").trim());
		dataSource.setMaxActive(Integer.parseInt(env.getProperty("spring.datasource.maxActive").trim()));
		dataSource.setInitialSize(Integer.parseInt(env.getProperty("spring.datasource.initialSize").trim()));
		dataSource.setMaxWait(Long.parseLong(env.getProperty("spring.datasource.maxWait").trim()));
		dataSource.setMinIdle(Integer.parseInt(env.getProperty("spring.datasource.minIdle").trim()));
		dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("spring.datasource.timeBetweenEvictionRunsMillis").trim()));
		dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("spring.datasource.minEvictableIdleTimeMillis").trim()));
		dataSource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("spring.datasource.testOnBorrow").trim()));
		dataSource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("spring.datasource.testOnReturn").trim()));
		dataSource.setValidationQuery(env.getProperty("spring.datasource.validationQuery").trim());
		Properties properties = new Properties();
		properties.setProperty("druid.stat.slowSqlMillis","5000");
		dataSource.setConnectProperties(properties);
		return dataSource;
	}


	@Bean
	public PageInterceptor pageInterceptor() {
		PageInterceptor interceptor = new PageInterceptor();
		Properties p = new Properties();
		p.setProperty("helperDialect", "mysql");
		p.setProperty("reasonable", "true");
		p.setProperty("supportMethodsArguments", "true");
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("params", "count=countSql");
		p.setProperty("autoRuntimeDialect", "true");
		p.setProperty("pageSizeZero", "true");
		interceptor.setProperties(p);
		return interceptor;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		Interceptor[] plugins = new Interceptor[] { pageInterceptor() };
		sqlSessionFactoryBean.setPlugins(plugins);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.spring.boot.mybatis.entity");
		return sqlSessionFactoryBean.getObject();
	}
	// 按照BeanId来拦截配置 用来bean的监控
	@Bean(value = "druid-stat-interceptor")
	public DruidStatInterceptor DruidStatInterceptor() {
		DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();
		return druidStatInterceptor;
	}



	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
		BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
		beanNameAutoProxyCreator.setProxyTargetClass(true);
		// 设置要监控的bean的id
		beanNameAutoProxyCreator.setBeanNames("userServiceImpl");
		beanNameAutoProxyCreator.setInterceptorNames("druid-stat-interceptor");
		return beanNameAutoProxyCreator;
	}


}
