package com.apex.mavenproject;

import java.io.IOException;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
 
public class CustomerGETApiTest {
	public static void main(String[] args) throws ClientProtocolException, IOException {
	
		//Create apache HTTP client
		HttpClient client = HttpClientBuilder.create().build();
		
		//Create a request message and send to the service
		//HttpGet request= new HttpGet("https://gorest.co.in/public/v2/users/5312");
		
		HttpGet request= new HttpGet("https://gorest.co.in/public/v2/users");
		
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		//Get the response
		HttpResponse response = client.execute(request);
		
		//validate the response
		System.out.println("StatusLine = "+ response.getStatusLine() +
		"\nStatuusCode = " +response.getStatusLine().getStatusCode()
		+"\nReasonPhrase = " +response.getStatusLine().getReasonPhrase()
		+"\nProtocolVersion = " +response.getStatusLine().getProtocolVersion()
		+"\nToString = " +response.getStatusLine().toString()
		);
		
		//get the response entity
		HttpEntity entity = response.getEntity();
		String result = "";
		if (entity != null)
		{
			result = EntityUtils.toString(entity);
			System.out.println("Entity = " + entity);
			System.out.println("Result = " +result);
		}
		
		//boolean isTestPassed = result.contains("Karunambika");
		System.out.println("*********************************" + 
		"\n Test Results"
		+"*********************************");
		
		//validate status code
		int statusCode=response.getStatusLine().getStatusCode();
		String statusMessage = response.getStatusLine().getReasonPhrase();
		if (statusCode==200 && statusMessage.equals("OK")) {
			System.out.println("StatusCode Test passed");
		}
		else {
			System.out.println("Statuscode Test failed");
		}
		
		//Validate user\
		boolean testPassed = result.contains("Karunambika");
		if(testPassed) {
			System.out.println("Valid User");
		}
		else {
			System.out.println("Test failed");
		}
		
	}
	
}
