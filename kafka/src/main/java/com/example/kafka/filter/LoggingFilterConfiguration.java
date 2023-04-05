package com.example.kafka.filter;

import javax.servlet.FilterRegistration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Order(0)
@Configuration
public class LoggingFilterConfiguration {

	@Bean
	public FilterRegistrationBean logingFilterReg() {
		FilterRegistrationBean filter = new FilterRegistrationBean<>();
		filter.setFilter(logingFilter());
		filter.setName("logingFilter");
		filter.setOrder(1);
		return filter;
	}

	@Bean(name = "logingFilter")
	public LoggingContextFilter logingFilter() {
		return new LoggingContextFilter();
	}

}
