package com.app.exceptionHandler;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class MyException extends RuntimeException {
	private String message;
	private int ResponseCode;
	private List<String> fieldErrors;

	public MyException(String message, int responseCode) {
		super(message);
		this.message = message;
		ResponseCode = responseCode;
	}

}
