package com.app.dbconfig2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class MultiDataBaseConfig {
	 @Bean(name = "datasource1")
	    @ConfigurationProperties("spring.datasource.db1")
	    @Primary
	    public DataSource dataSource(){
	        return DataSourceBuilder.create().build();
	    }
	    @Bean(name = "datasource2")
	    @ConfigurationProperties("spring.datasource.db2")
	    public DataSource dataSource2(){
	        return DataSourceBuilder.create().build();
	    }
	    @Bean(name="tm1")
	    @Autowired
	    @Primary
	    DataSourceTransactionManager tm1(@Qualifier ("datasource1") DataSource datasource) {
	        DataSourceTransactionManager txm  = new DataSourceTransactionManager(datasource);
	        return txm;
	    }
	    @Bean(name="tm2")
	    @Autowired
	    DataSourceTransactionManager tm2(@Qualifier ("datasource2") DataSource datasource) {
	        DataSourceTransactionManager txm  = new DataSourceTransactionManager(datasource);
	        return txm;
	    }
}
