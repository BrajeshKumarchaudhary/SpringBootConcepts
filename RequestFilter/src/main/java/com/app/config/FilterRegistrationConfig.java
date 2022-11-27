package com.app.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.app.filter.CryptoFilter;
import com.app.filter.LoggingFilter;
import com.app.filter.PermissionFilter;

/**
 * This Class Register ,Multiple Request Filter
 * 
 * @author brajesh
 *
 */
@Configuration
public class FilterRegistrationConfig {

	@Autowired
	Environment env;
	private final String logUrlPattern = "app.enable.logging.pattern";
	private final String cryptoPatternKey = "app.enable.crypto.pattern";
	private final String permissionPatternKey = "app.enable.permission.pattern";

	/**
	 * Register Logging Filter For Which Url Request logging Enable
	 * 
	 * @return
	 */

	@ConditionalOnProperty(name = "app.logging.enable", havingValue = "1")
	public FilterRegistrationBean registerLogingFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean<>();
		registration.setFilter(getLoggingFilter());
		registration.addUrlPatterns(getUrlPattern(logUrlPattern));
		registration.setName("loggingFilter");
		registration.setOrder(1);
		return registration;
	}
	/**
	 * Register Request Encryption and Decryption
	 * @return
	 */

	@ConditionalOnProperty(name = "app.crypto.enable", havingValue = "1")
	public FilterRegistrationBean registerCryptoFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean<>();
		registration.setFilter(getCryptoFilter());
		registration.addUrlPatterns(getUrlPattern(cryptoPatternKey));
		registration.setName("cryptoFilter");
		registration.setOrder(2);
		return registration;
	}

	/**
	 * Register Request Permission Filter
	 * @return
	 */

	@ConditionalOnProperty(name = "app.permission.filter.enable", havingValue = "1")
	public FilterRegistrationBean registerPermissionFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean<>();
		registration.setFilter(getCryptoFilter());
		registration.addUrlPatterns(getUrlPattern(permissionPatternKey));
		registration.setName("permissionFilter");
		registration.setOrder(3);
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

	@Bean(name = "cryptoFilter")
	@ConditionalOnProperty(name = "app.crypto.enable", havingValue = "1")
	public CryptoFilter getCryptoFilter() {
		return new CryptoFilter();
	}
	
	@Bean(name = "permissionFilter")
	@ConditionalOnProperty(name = "app.permission.filter.enable", havingValue = "1")
	public PermissionFilter getPermissionFilter() {
		return new PermissionFilter();
	}
}
