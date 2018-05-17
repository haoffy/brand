package com.ls.brand.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourceConifg {

	
	@Bean(name = "datasource")  
    @ConfigurationProperties(prefix="spring.datasource")  
    public DataSource primaryDataSource(){  
        return new DruidDataSource();  
    }  
	
}
