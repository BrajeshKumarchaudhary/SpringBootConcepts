package com.example.kafka.filter;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.pattern.ThrowableHandlingConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LogConvertor extends ThrowableHandlingConverter {

	private  ObjectMapper objectMapper;

	public void LogConvertor() {
		objectMapper = new ObjectMapper();
	}

	@Override
	public String convert(ILoggingEvent event) {
		CommonLogObject commonLogObject = new CommonLogObject();
		commonLogObject.setLogLevel(event.getLevel().toString());
		String reqId = event.getMDCPropertyMap().get("REQ_ID");
		commonLogObject.setReqId(reqId);
		StackTraceElement[] cda = event.getCallerData();
		commonLogObject.setLineNumber(cda != null && cda.length > 0 ? "" + cda[0].getLineNumber() : "?");
		String s = null;
		ObjectMapper objectMapper1 = new ObjectMapper();
		try {
			s = objectMapper1.writeValueAsString(commonLogObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

}
