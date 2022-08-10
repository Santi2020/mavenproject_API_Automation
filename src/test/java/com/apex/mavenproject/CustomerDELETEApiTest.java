package com.apex.mavenproject;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;

public class CustomerDELETEApiTest {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpDelete request = new HttpDelete("https://gorest.co.in/public/v2/users/2642");
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		HttpResponse response = client.execute(request);
		System.out.println("Response =" + response.getStatusLine().getStatusCode());
		
	}
	
	public void temp() {
		
		String a="aa";
		
		try {
			if (a.equals("aa")) {
				System.out.println("A is initialized");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
