package com.apex.mavenproject;

import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
//import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.json.JSONObject;  
import org.json.JSONArray;  

//import org.apache.http.message.BasicNameValuePair;

public class CustomerPOSTApiTest {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("https://gorest.co.in/public/v2/users");
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		 
		urlParameters.add(new BasicNameValuePair("name", "Asha21"));
		urlParameters.add(new BasicNameValuePair("email", "Asha121@gmmmm.com"));
		urlParameters.add(new BasicNameValuePair("gender", "female"));
		urlParameters.add(new BasicNameValuePair("status", "active"));
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		
		System.out.println("checking entity");
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // return it as a String
            String result = EntityUtils.toString(entity);
            System.out.println(result);
            JSONArray array = new JSONArray(result);  
            for(int i=0; i < array.length(); i++)   
            {  
            JSONObject object = array.getJSONObject(i);  
            System.out.println(object.getString("field") + " " + object.getString("message"));  
            //System.out.println(object.getString("message"));  
            }  
              
            //entity.
        }else {
        	System.out.println("entity is null");
        }
		
		String statusMessage= response.getStatusLine().getReasonPhrase();
		int statuscode= response.getStatusLine().getStatusCode();
		
		if (statuscode == 201 ) {
			System.out.println("Test passed");
		}
		else{
			System.out.println("Test failed");
		}
						
		System.out.println("StatusCode = " + response.getStatusLine().getStatusCode()
				+ "\nReasonPhrase = " +response.getStatusLine().getReasonPhrase()
		);
	}
}
