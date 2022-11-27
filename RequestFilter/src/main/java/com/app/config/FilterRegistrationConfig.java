package com.app.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.app.filter.LoggingFilter;

@Configuration
public class FilterRegistrationConfig {

	@Autowired
	Environment env;
	private final String logUrlPattern="app.enable.logging.pattern";

	@ConditionalOnProperty(name = "app.logging.enable", havingValue = "1")
	public FilterRegistrationBean registerLogingFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean<>();
		registration.setFilter(getLoggingFilter());
		registration.addUrlPatterns(getUrlPattern(logUrlPattern));
		registration.setName("loggingFilter");
		registration.setOrder(1);
		return registration;
	}

	public String[] getUrlPattern(String key) {
		return env.getProperty(key, "/test").split(",");

	}

	@Bean(name = "loggingFilter")
	@ConditionalOnProperty(name = "app.logging.enable", havingValue = "1")
	public LoggingFilter getLoggingFilter() {
		return new LoggingFilter();
	}

}
