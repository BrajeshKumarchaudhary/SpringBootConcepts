package com.app.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public  class LoggingFilter extends OncePerRequestFilter {
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		    printLog(request);
		    filterChain.doFilter(request, response);
		
	}

	private void printLog(HttpServletRequest request) {
		System.out.println("Request:"+request.getLocalAddr());
		
	}

}
