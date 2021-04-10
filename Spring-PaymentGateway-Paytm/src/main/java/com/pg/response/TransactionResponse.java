package com.pg.response;

import java.util.TreeMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
	private String message;
	private int responseCode;
	private TreeMap<String, String> parameters;
}
