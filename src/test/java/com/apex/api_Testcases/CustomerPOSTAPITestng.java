package com.apex.api_Testcases;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.apex.api_Resources.CustomerConstants;
import com.apex.api_core.Validate;

public class CustomerPOSTAPITestng implements CustomerConstants {
	
	String allMessage= "Test response code. \nTest                StatusCode       ReasonPhrase"; 
	String testName=null;
	
	SoftAssert softAssert = new SoftAssert();
	
	
	@Test
	public void testWithCorrectData() throws ClientProtocolException, IOException {
		testName = "testWithCorrectData";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(BASE_URL);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		
		String randStr = randomString();
		urlParameters.add(new BasicNameValuePair("name", "Asha"+randStr));
		urlParameters.add(new BasicNameValuePair("email", "Asha"+ randStr +"@gmmmm.com"));
		urlParameters.add(new BasicNameValuePair("gender", "female"));
		urlParameters.add(new BasicNameValuePair("status", "active"));
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		
		composeAllMessage(response);
		System.out.println(STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
		//Validate.performBasicValidation(response, STATUS_CO, randStr);
	
		Validate.performBasicValidation(response,STATUS_CODE_201, STATUS_MESSAGE_CREATED);
		
	}
	
	@Test
	public void testWithExistingData() throws ClientProtocolException, IOException {
		testName = "testWithExistingData";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(BASE_URL);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		
		urlParameters.add(new BasicNameValuePair("name", "Asha4"));
		urlParameters.add(new BasicNameValuePair("email", "Asha4@gmmmm.com"));
		urlParameters.add(new BasicNameValuePair("gender", "female"));
		urlParameters.add(new BasicNameValuePair("status", "active"));
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		
		composeAllMessage(response);
		
		
		Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
	}	
	
	@Test
	public void testWithNameOnly() throws ClientProtocolException, IOException {
		testName = "testWithNameOnly";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(BASE_URL);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		
		String randStr = randomString();
		urlParameters.add(new BasicNameValuePair("name", "Asha"+randStr));
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		
		composeAllMessage(response);
		
		Validate.performBasicValidation(response, STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
		//Validate.performBasicValidation(response,STATUS_CODE_422, );
	}

	

	@Test
	public void testWithEmailOnly() throws ClientProtocolException, IOException {
		testName="testWithEmailOnly";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(BASE_URL);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		
		String randStr = randomString();

		urlParameters.add(new BasicNameValuePair("email", "Asha"+ randStr +"@gmmmm.com"));
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		composeAllMessage(response);
		
		
		Validate.performBasicValidation(response , STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
	
	}
	
	@Test
	public void testWithGenderOnly() throws ClientProtocolException, IOException {
		testName = "testWithGenderOnly";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(BASE_URL);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		
		String randStr = randomString();
		urlParameters.add(new BasicNameValuePair("gender", "female"));
		
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		
		composeAllMessage(response);
		
		Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);	
	}
	
	@Test
	public void testWithStatusOnly() throws ClientProtocolException, IOException {
		testName = "testWithStatusOnly";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(BASE_URL);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		
		urlParameters.add(new BasicNameValuePair("status", "active"));
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		
		composeAllMessage(response);
		
		Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
	
	}
	
	@Test
	public void testWithExistingEmail() throws ClientProtocolException, IOException {
		testName = "testWithExistingEmail";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(BASE_URL);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		
		String randStr = randomString();
		urlParameters.add(new BasicNameValuePair("name", "ExistingEmail"+randStr));
		urlParameters.add(new BasicNameValuePair("email", "Trivedi1234@yahoo.com"));
		urlParameters.add(new BasicNameValuePair("gender", "female"));
		urlParameters.add(new BasicNameValuePair("status", "active"));
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		
		composeAllMessage(response);
		
		Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
		
	}
	
	@Test
	public void testWithIncorrectGender() throws ClientProtocolException, IOException {
		testName = "testWithIncorrectGender";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(BASE_URL);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		
		String randStr = randomString();
		urlParameters.add(new BasicNameValuePair("name", "Asha"+randStr));
		urlParameters.add(new BasicNameValuePair("email", "Asha"+ randStr +"@gmmmm.com"));
		urlParameters.add(new BasicNameValuePair("gender", "femaleAAA"));
		urlParameters.add(new BasicNameValuePair("status", "active"));
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		
		composeAllMessage(response);
				
		Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
	}
	
	@Test
	public void testWithIncorrectStatus() throws ClientProtocolException, IOException {
		testName = "testWithIncorrectStatus";
	
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(BASE_URL);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		
		String randStr = randomString();
		urlParameters.add(new BasicNameValuePair("name", "Asha"+randStr));
		urlParameters.add(new BasicNameValuePair("email", "Asha"+ randStr +"@gmmmm.com"));
		urlParameters.add(new BasicNameValuePair("gender", "female"));
		urlParameters.add(new BasicNameValuePair("status", "activeAAA"));
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		
		composeAllMessage(response);
				

		Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
		
	}
	
	@Test
	public void testWithLengthyName() throws ClientProtocolException, IOException {
		testName = "testWithLengthyName";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(BASE_URL);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		
		String randStr = randomString();
		
		String computeName= "Aaabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmn" + randStr;
		System.out.println("Length of computeName=" + computeName.length());
		
		urlParameters.add(new BasicNameValuePair("name", computeName));
		urlParameters.add(new BasicNameValuePair("email", "Asha"+ randStr +"@gmmmm.com"));
		urlParameters.add(new BasicNameValuePair("gender", "female"));
		urlParameters.add(new BasicNameValuePair("status", "active"));
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		
		composeAllMessage(response);
				
		Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
	}
	
	@Test
	public void testWithLengthyEmail() throws ClientProtocolException, IOException {
		testName = "testWithLengthyEmail";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(BASE_URL);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		
		String randStr = randomString();
		urlParameters.add(new BasicNameValuePair("name", "Abcde"+randStr));
		String computeEmail= "A6143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decd"+ randStr +"@gamil.com";
		System.out.println("Length of computeEmail=" + computeEmail.length());
		
		urlParameters.add(new BasicNameValuePair("email", computeEmail ));
		urlParameters.add(new BasicNameValuePair("gender", "female"));
		urlParameters.add(new BasicNameValuePair("status", "active"));
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		
		composeAllMessage(response);
		
		Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
	}
	
	@Test
	public void testWithMissingSymbol() throws ClientProtocolException, IOException {
		testName = "testWithMissingSymbol";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(BASE_URL);
		
		request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
		
		List <NameValuePair> urlParameters = new ArrayList<>();
		
		String randStr = randomString();
		urlParameters.add(new BasicNameValuePair("name", "Asha"+randStr));
		urlParameters.add(new BasicNameValuePair("email", "Asha"+ randStr +"gmmmm.com"));
		urlParameters.add(new BasicNameValuePair("gender", "female"));
		urlParameters.add(new BasicNameValuePair("status", "active"));
		
		request.setEntity(new UrlEncodedFormEntity(urlParameters));
		HttpResponse response = client.execute(request);
		
		composeAllMessage(response);
				
		Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
	}

@Test
public void testWithMissingDomain() throws ClientProtocolException, IOException {
	testName = "testWithMissingDomain";
	HttpClient client = HttpClientBuilder.create().build();
	HttpPost request = new HttpPost(BASE_URL);
	
	request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
	
	List <NameValuePair> urlParameters = new ArrayList<>();
	
	String randStr = randomString();
	urlParameters.add(new BasicNameValuePair("name", "Asha"+randStr));
	urlParameters.add(new BasicNameValuePair("email", "Asha"+ randStr +"@"));
	urlParameters.add(new BasicNameValuePair("gender", "female"));
	urlParameters.add(new BasicNameValuePair("status", "active"));
	
	request.setEntity(new UrlEncodedFormEntity(urlParameters));
	HttpResponse response = client.execute(request);
	
	composeAllMessage(response);
			
	Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
}


@Test
public void testWithMultipleSymbol() throws ClientProtocolException, IOException {
	testName = "testWithMultipleSymbol";
	HttpClient client = HttpClientBuilder.create().build();
	HttpPost request = new HttpPost(BASE_URL);
	
	request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
	
	List <NameValuePair> urlParameters = new ArrayList<>();
	
	String randStr = randomString();
	urlParameters.add(new BasicNameValuePair("name", "Asha"+randStr));
	urlParameters.add(new BasicNameValuePair("email", "Asha@"+ randStr +"@gmail.com"));
	urlParameters.add(new BasicNameValuePair("gender", "female"));
	urlParameters.add(new BasicNameValuePair("status", "active"));
	
	request.setEntity(new UrlEncodedFormEntity(urlParameters));
	HttpResponse response = client.execute(request);
	
	composeAllMessage(response);
			
	Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);

}



@Test
public void testWithBlankName() throws ClientProtocolException, IOException {
	testName = "testWithBlankName";
	HttpClient client = HttpClientBuilder.create().build();
	HttpPost request = new HttpPost(BASE_URL);
	
	request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
	
	List <NameValuePair> urlParameters = new ArrayList<>();
	
	String randStr = randomString();
	urlParameters.add(new BasicNameValuePair("name", ""));
	urlParameters.add(new BasicNameValuePair("email", "Asha"+ randStr +"@gmmmm.com"));
	urlParameters.add(new BasicNameValuePair("gender", "female"));
	urlParameters.add(new BasicNameValuePair("status", "active"));
	
	request.setEntity(new UrlEncodedFormEntity(urlParameters));
	HttpResponse response = client.execute(request);
	
	composeAllMessage(response);
			
	Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
	
}

@Test
public void testWithBlankEmail() throws ClientProtocolException, IOException {
	testName = "testWithBlankEmail";
	HttpClient client = HttpClientBuilder.create().build();
	HttpPost request = new HttpPost(BASE_URL);
	
	request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
	
	List <NameValuePair> urlParameters = new ArrayList<>();
	
	String randStr = randomString();
	urlParameters.add(new BasicNameValuePair("name", "AnewUser"));
	//urlParameters.add(new BasicNameValuePair("email", "Asha"+ randStr +"@gmmmm.com"));
	urlParameters.add(new BasicNameValuePair("email", ""));
	urlParameters.add(new BasicNameValuePair("gender", "female"));
	urlParameters.add(new BasicNameValuePair("status", "active"));
	
	request.setEntity(new UrlEncodedFormEntity(urlParameters));
	HttpResponse response = client.execute(request);
	
	composeAllMessage(response);
			
	Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
}


@Test
public void testWithBlankGender() throws ClientProtocolException, IOException {
	testName = "testWithBlankGender";
	HttpClient client = HttpClientBuilder.create().build();
	HttpPost request = new HttpPost(BASE_URL);
	
	request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
	
	List <NameValuePair> urlParameters = new ArrayList<>();
	
	String randStr = randomString();
	urlParameters.add(new BasicNameValuePair("name", "AnewUser"));
	urlParameters.add(new BasicNameValuePair("email", "Asha"+ randStr +"@gmmmm.com"));
	urlParameters.add(new BasicNameValuePair("gender", ""));
	urlParameters.add(new BasicNameValuePair("status", "active"));
	
	request.setEntity(new UrlEncodedFormEntity(urlParameters));
	HttpResponse response = client.execute(request);
	
	composeAllMessage(response);
			
	Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
	
}

/**
 * @param response
 */
public void composeAllMessage(HttpResponse response) {
	allMessage=allMessage + "\n" +testName +" -- "  + response.getStatusLine().getStatusCode()
	+ "       " +response.getStatusLine().getReasonPhrase()		;
}



@Test(priority=2)
public void testWithBlankStatus() throws ClientProtocolException, IOException {
	testName = "testWithBlankStatus";
	HttpClient client = HttpClientBuilder.create().build();
	HttpPost request = new HttpPost(BASE_URL);
	
	request.setHeader("Authorization", "Bearer ac008d3b46143c71c281ca6d326232c1a863981fde6004cebf805d509decdb88");
	
	List <NameValuePair> urlParameters = new ArrayList<>();
	
	String randStr = randomString();
	urlParameters.add(new BasicNameValuePair("name", "AnewUser"));
	urlParameters.add(new BasicNameValuePair("email", "Asha"+ randStr +"@gmmmm.com"));
	urlParameters.add(new BasicNameValuePair("gender", "female"));
	urlParameters.add(new BasicNameValuePair("status", ""));
	
	request.setEntity(new UrlEncodedFormEntity(urlParameters));
	HttpResponse response = client.execute(request);
	
	composeAllMessage(response);
	
	Validate.performBasicValidation(response,STATUS_CODE_422, STATUS_MESSAGE_UNPROCESSABLE_ENTITY);
}

	@Test(priority=1)
	public void testWith_incomplete() throws ClientProtocolException, IOException {
		testName="testWith_incomplete";
		allMessage=allMessage + "\n" +testName +" -- "  + "soft"
				+ "       Assert -1" 		;
				
		softAssert.assertTrue(false);
		//softAssert.assertEquals("actualUrl", "expectedUrl", "Comaping expectedURL with ActualUrl");
		//System.out.println("\n\nSoft asserts--1");
		softAssert.assertAll();
		//System.out.println("\n\n");
	}
	
	@Test
	public void testWithSoftAssertions_incomplete() throws ClientProtocolException, IOException {
		
		testName="testWithSoftAssertions_incomplete";
		allMessage=allMessage + "\n" +testName +" -- "  + "soft"
				+ "       Assert -2" 		;
		
		softAssert.assertTrue(false);
	
		System.out.println("\n\nSoft asserts--2");
		softAssert.assertAll();
		System.out.println("\n\n");
	}
	
	@AfterTest
	public void displayOutput() throws ClientProtocolException, IOException {
		System.out.println("\n\n");
		System.out.println("********************************************************");
		System.out.println(allMessage);
		System.out.println("********************************************************");
		System.out.println("\n\n");
		
	}
	
	public String randomString() {
		String generatedString =RandomStringUtils.randomAlphabetic(10);
		return generatedString;
	}
		
}
