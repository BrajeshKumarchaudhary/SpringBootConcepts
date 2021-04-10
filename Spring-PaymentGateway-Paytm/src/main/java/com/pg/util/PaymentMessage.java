package com.pg.util;

public class PaymentMessage {
	public static final int SUCCESS_TXN_CODE = 1000;
	public static final String SUCCESS_TXN_MESSAGE = "Payment Successful";

	public static final int FAILED_TXN_CODE = 1001;
	public static final String FAILED_TXN_MESSAGE = "Payment Failed";
	
	public static final int CHECKSUM_TXN_CODE = 1002;
	public static final String CHECKSUM_TXN_MESSAGE = "Checksum mismatched";
	
	public static final int TXN_EXCEPTION_CODE = 1003;
	public static final String TXN_EXCEPTION_MESSAGE = "";

	public static final int REQUEST_INVALID_CODE = 2000;
	public static final String REQUEST_INVALID_MESSAGE = "Invalid Request";
}
