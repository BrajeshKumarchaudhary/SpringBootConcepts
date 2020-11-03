package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logger);
        registry.addInterceptor(apiKeyToken);
    }
}
