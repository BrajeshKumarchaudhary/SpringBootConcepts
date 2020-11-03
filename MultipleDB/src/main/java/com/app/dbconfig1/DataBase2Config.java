package com.app.dbconfig1;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.app.db2.repo",
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef= "db2TransactionManager"
)
public class DataBase2Config {
	 @Bean
	    @Primary
	    @ConfigurationProperties("spring.datasource.db2")
	    public DataSourceProperties db2DataSourceProperties() {
	        return new DataSourceProperties();
	    }
	    @Bean
	    @Primary
	    @ConfigurationProperties("spring.datasource.db2.configuration")
	    public DataSource db2DataSource() {
	        return db2DataSourceProperties().initializeDataSourceBuilder()
	                .type(HikariDataSource.class).build();
	    }
	    @Primary
	    @Bean(name = "db2EntityManagerFactory")
	    public LocalContainerEntityManagerFactoryBean sbsEntityManagerFactory(EntityManagerFactoryBuilder builder) {
	        return builder
	                .dataSource(db2DataSource())
	                .packages("com.app.db2.entity")
	                .build();
	    }
	    @Primary
	    @Bean
	    public PlatformTransactionManager sbsTransactionManager(
	            final @Qualifier("db2EntityManagerFactory") LocalContainerEntityManagerFactoryBean sbsEntityManagerFactory) {
	        return new JpaTransactionManager(sbsEntityManagerFactory.getObject());
	    }

}
