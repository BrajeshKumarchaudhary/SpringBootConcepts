package com.product.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandeller {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleArithmeticException(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<Object>("Something went wrong!", HttpStatus.BAD_REQUEST);
	}
}
