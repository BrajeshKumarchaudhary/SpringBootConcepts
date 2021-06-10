package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// disable CSRF
		http.csrf().disable().authorizeRequests().
		// allow
				antMatchers("/swagger-ui.html").permitAll().antMatchers("/").permitAll().antMatchers("/css/*")
				.permitAll().antMatchers("/js/*").permitAll().
				// disAllowanything else
				anyRequest().authenticated().and().httpBasic();

		// No session will be created
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("bk").password("{noop}12345").roles("ADMIN");
	}
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}

}
