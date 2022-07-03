package com.app.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.app.interceptor.ApiTokenInterceptor;
import com.app.interceptor.LoggerInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	LoggerInterceptor logger;
	@Autowired
	ApiTokenInterceptor apiKeyToken;
	
	@Autowired
	Environment env;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logger);
        registry.addInterceptor(apiKeyToken).addPathPatterns(getUrlPattern());
    }
	
	public List<String> getUrlPattern(){
		 return Arrays.asList(env.getProperty("client.authentication.url","/test").split(","));
	}
	
}
