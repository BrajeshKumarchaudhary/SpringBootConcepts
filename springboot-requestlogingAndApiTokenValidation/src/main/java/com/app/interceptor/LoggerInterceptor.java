package com.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggerInterceptor implements HandlerInterceptor {
	Logger logger=LoggerFactory.getLogger(LoggerInterceptor.class);
	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
	                             Object handler) {

	        logger.info("#RemoteAddr--"+request.getRemoteAddr());
	        logger.info("#Header--"+request.getHeaderNames());
	        logger.info("#ContenetType--"+request.getContentType());
	        logger.info("#Contentlength--"+request.getContentLength());
	        logger.info("#Cookies--"+request.getCookies());
	        logger.info("#getContextPath--"+request.getContextPath());
	        logger.info("#getRemoteHost--"+request.getRemoteHost());
	        String token=request.getParameter("key");
	            System.out.println(token);
	        return true;
	    }
}
