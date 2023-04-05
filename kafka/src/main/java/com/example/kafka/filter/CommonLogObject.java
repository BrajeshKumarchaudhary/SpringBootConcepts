package com.example.kafka.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonLogObject {
	private String logLevel;
	private String className;
	private String lineNumber;
	private String reqId;
}
