package com.apex.mavenproject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

//import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class CustomerPUTApiTest {
 public static void main(String[] args) throws ClientProtocolException, IOException {
	HttpClient client = HttpClientBuilder.create().build();
	HttpPut request = new HttpPut("https://gorest.co.in/public/v2/users/3605");
	
	request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
	                                        
	List <NameValuePair> urlParameters = new ArrayList<>();
	urlParameters.add(new BasicNameValuePair("email", "change_karun12a@yahoo.com"));
	urlParameters.add(new BasicNameValuePair("gender", "female"));
	urlParameters.add(new BasicNameValuePair("status", "active"));
	
	request.setEntity(new UrlEncodedFormEntity(urlParameters));
	HttpResponse response = client.execute(request);
	
	System.out.println("Response = " + response.getStatusLine());
}
}
