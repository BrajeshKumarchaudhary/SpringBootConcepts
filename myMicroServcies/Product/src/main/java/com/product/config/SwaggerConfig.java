package com.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {
	@Value("${swagger.enable}")
	private boolean pluginEnable;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().build().apiInfo(apiInfo()).enable(pluginEnable)
				.useDefaultResponseMessages(false);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Product API System").description("List of APIs in product System")
				.version("1.0.0").termsOfServiceUrl("").license("Technologies").licenseUrl("com").build();
	}
//	@Bean 
//	public DocumentationCache cache() {
//		return new DocumentationCache();
//	}
}
