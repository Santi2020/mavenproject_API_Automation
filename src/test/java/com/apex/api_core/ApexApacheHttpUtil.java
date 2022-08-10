package com.apex.api_core;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class ApexApacheHttpUtil {

	/**
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static HttpResponse sendAndReceiveHTTPGetMSG(String url) throws IOException, ClientProtocolException {
		HttpResponse response;
		//Create apache HTTP client
		HttpClient client = HttpClientBuilder.create().build();
		
		//Create a request message and send to the service
		//HttpGet request= new HttpGet("https://gorest.co.in/public/v2/users/5312");
		
		HttpGet request= new HttpGet(url);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		//Get the response
		response = client.execute(request);
		return response;
	}
	
	
	/**
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public static String getResposeMSGFromHTTPResponse(HttpResponse response) throws IOException {
		//get the response entity
		HttpEntity entity = response.getEntity();
		String result = "";
		if (entity != null)
		{
			result = EntityUtils.toString(entity);
			System.out.println("Entity = " + entity);
			System.out.println("Result = " +result);
		}
		return result;
	}
}
