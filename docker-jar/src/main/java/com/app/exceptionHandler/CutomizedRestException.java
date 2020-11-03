package com.app.exceptionHandler;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CutomizedRestException extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ MyException.class })
	public ResponseEntity<Object> handlException(Exception ex, WebRequest request) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		MyException exception = new MyException();
		exception.setMessage(ex.getMessage());
		exception.setResponseCode(200);
		return new ResponseEntity<Object>(exception, headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> Exception(Exception ex, WebRequest request) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		MyException exception = new MyException();
		exception.setMessage(ex.getMessage());
		exception.setResponseCode(200);
		return new ResponseEntity<Object>(exception, headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> ValidationException(Exception ex, WebRequest request) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		MyException exception = new MyException();
		exception.setMessage(ex.getMessage());
		exception.setResponseCode(200);
		return new ResponseEntity<Object>(exception, headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		HttpHeaders header = new HttpHeaders();
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
		MyException exception = new MyException();
		exception.setMessage("Invalid Request Due to given Field Errors");
		exception.setFieldErrors(errors);
		exception.setResponseCode(200);
		return new ResponseEntity<Object>(exception, headers, HttpStatus.INTERNAL_SERVER_ERROR);   }
}
