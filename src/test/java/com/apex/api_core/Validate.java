package com.apex.api_core;

import org.apache.http.HttpResponse;
import org.junit.Assert;

public class Validate {
	/**
	 * @param response
	 */
	public static void performBasicValidationNW(HttpResponse response, int expStatusCode, String expStatusMsg) {
		Assert.assertEquals(expStatusCode, response.getStatusLine().getStatusCode());
		Assert.assertEquals(expStatusMsg, response.getStatusLine().getReasonPhrase());
	}

	public static void performBasicValidation(HttpResponse response,  int expStatusCode, String expStatusMsg) {
		// TODO Auto-generated method stub
		Assert.assertEquals(expStatusCode, response.getStatusLine().getStatusCode());
		Assert.assertEquals(expStatusMsg, response.getStatusLine().getReasonPhrase());
		
	}
}
