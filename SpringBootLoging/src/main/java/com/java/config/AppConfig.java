package com.java.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.bean.Operate;
import com.java.bean.Person;

@Configuration
public class AppConfig {

	/*
	 * We can Specify bean init and destroy method
	 */
	@Bean(name = "op",initMethod = "operateInit",destroyMethod = "operateDown")
	@ConditionalOnBean(name = "person")
	public Operate operate() {
		return new Operate();
	}
	
	@Bean(name = "perosn")
	public Person person() {
		return new Person();
	}
}
