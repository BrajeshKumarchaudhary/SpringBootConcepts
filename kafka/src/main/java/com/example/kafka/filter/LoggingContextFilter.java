package com.example.kafka.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class LoggingContextFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String rqId = request.getHeader("REQ_ID");
			if (ObjectUtils.isEmpty(rqId)) {
				rqId = UUID.randomUUID().toString().replace("-", "");
			}
			System.out.println("==================RequestId:=================="+rqId);
			MDC.put("REQ_ID", rqId);
			System.out.println("RequestId:"+rqId);
			filterChain.doFilter(request, response);
		} finally {
			MDC.remove("REQ_ID");
		}

	}

}
