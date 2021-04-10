package com.pg.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object handler) throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			if (httpServletRequest.getRequestURI().contains("swagger")) {
				return HandlerInterceptor.super.preHandle(httpServletRequest, httpServletResponse, handler);
			}

//            logger.error("handler is not instance of HandlerMethod : {}", httpServletRequest.getRequestURI());
			return false;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		VerifyClientToken verifyClientToken = handlerMethod.getMethod().getAnnotation(VerifyClientToken.class);

		if (verifyClientToken == null) {
			return HandlerInterceptor.super.preHandle(httpServletRequest, httpServletResponse, handler);
		}

		// Check for token
		if (verifyClientToken != null) {
			verifyClientToken(httpServletRequest);
		}
		return HandlerInterceptor.super.preHandle(httpServletRequest, httpServletResponse, handler);
	}

	private void verifyClientToken(HttpServletRequest httpServletRequest)
			throws MissingServletRequestParameterException {
		String token = httpServletRequest.getHeader("clientSecret");
		if (token == null || token.isEmpty()) {
			token = httpServletRequest.getParameter("clientSecret");

			if (token == null || token.isEmpty()) {
				throw new MissingServletRequestParameterException("Missing Token", "token");
			}
		}
		// Check for clientId
	}
}
