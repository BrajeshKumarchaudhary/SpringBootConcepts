package com.app.customendpoints;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator extends AbstractHealthIndicator  {

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		builder.up()
        .withDetail("app", "All Good!!")
        .withDetail("error", "No issue");
		
	}

}
