package com.pg.util;

import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.paytm.pg.merchant.PaytmChecksum;
import com.pg.config.PaytmDetails;

public class TransactionServiceHelper {

	public static boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum,
			PaytmDetails paytmDetails) throws Exception {
		return PaytmChecksum.verifySignature(parameters, paytmDetails.getMerchantKey(), paytmChecksum);
	}

	public static String getCheckSum(TreeMap<String, String> parameters, PaytmDetails paytmDetails) throws Exception {
		return PaytmChecksum.generateSignature(parameters, paytmDetails.getMerchantKey());
	}
}
