package com.java.cacheConfig;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheManager {

	@Bean
	public ConcurrentMapCacheManager cachemanager()
	{
		return new ConcurrentMapCacheManager("mycache");
	}
	
}
