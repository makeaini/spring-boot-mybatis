package com.spring.boot.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.Properties;

@Configuration
@MapperScan(value ="com.spring.boot.mybatis.mapper.cluster",sqlSessionFactoryRef = "clusterSqlSessionFactory")
@EnableTransactionManagement
public class ClusterDataSourceConfig implements EnvironmentAware{
	private Environment env;
	@Autowired
	private PageInterceptor pageInterceptor;
	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}

	@Bean(initMethod = "init", destroyMethod = "close",name = "clusterDataSource")
	public DruidDataSource clusterDataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(env.getProperty("cluster.datasource.driver-class-name").trim());
		dataSource.setUrl(env.getProperty("cluster.datasource.url").trim());
		dataSource.setUsername(env.getProperty("cluster.datasource.username").trim());
		dataSource.setPassword(env.getProperty("cluster.datasource.password").trim());
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
	@Bean(name = "clusterTransactionManager")
	public DataSourceTransactionManager clusterTransactionManager() throws SQLException {
		return new DataSourceTransactionManager(clusterDataSource());
	}
	@Bean(name = "clusterSqlSessionFactory")
	public SqlSessionFactory clusterSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(clusterDataSource());
		Interceptor[] plugins = new Interceptor[] { pageInterceptor};
		sqlSessionFactoryBean.setPlugins(plugins);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.spring.boot.mybatis.entity");
		return sqlSessionFactoryBean.getObject();
	}

}
