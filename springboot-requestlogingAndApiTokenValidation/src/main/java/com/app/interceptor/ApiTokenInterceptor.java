package com.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class ApiTokenInterceptor implements HandlerInterceptor {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
		
		if (handler instanceof HandlerMethod) {
            if (request.getRequestURI().contains("swagger")) {
                return HandlerInterceptor.super.preHandle(request, response, handler);
            }
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		ApiKeyVerify verifyApiToken = handlerMethod.getMethod().getAnnotation(ApiKeyVerify.class);
	    if(verifyApiToken!=null) {
	    	if(verifyApiKey(request,verifyApiToken,response)) {
	    		return HandlerInterceptor.super.preHandle(request, response, handler);		
	    	}
	    }
		}
		 return false;
    }

	private boolean verifyApiKey(HttpServletRequest request, ApiKeyVerify verifyApiToken,HttpServletResponse response) throws Exception {
        boolean isValid=false;
		String token = request.getHeader("Api-key");
        if (token == null || token.isEmpty()) {
        	
            throw new MissingServletRequestParameterException("clientSecret", "String");
        }
        if(!token.equalsIgnoreCase("API")) {
        	throw new Exception("Invalid API KEY");
        }
        return isValid;
	}

}
