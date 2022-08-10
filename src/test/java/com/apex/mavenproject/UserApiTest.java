package com.apex.mavenproject;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class UserApiTest {
	public static void main(String[] args) throws ClientProtocolException, IOException {
	// step1 create httpclient
	HttpClient client = HttpClientBuilder.create().build();
	
	// step2 create http request
	HttpGet request = new HttpGet("https://reqres.in/api/users/2");
	
	// step3 send and get response
	HttpResponse response =	client.execute(request);
	
	// step4 validate the response code
	System.out.println(response.getStatusLine().getStatusCode());
	System.out.println(response.getStatusLine().getReasonPhrase());
	
	HttpEntity entity= response.getEntity();
	if(entity!=null)
		{
			String result = EntityUtils.toString(entity);
			System.out.println(result);
		}
	
	}
	
}
