package com.apex.api_Testcases;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.apex.api_Resources.CustomerConstants;
import com.apex.api_core.ApexApacheHttpUtil;
import com.apex.api_core.Validate;
import com.apex.api_core.XLUtils;



public class CustomerGETAPITestng implements CustomerConstants {
  
	@Test(priority=1, dataProvider="correctids_dp")
	  public void testWithCorrecID(String userID, String name) throws ClientProtocolException, IOException {
		HttpResponse response =null;
		//String url = "https://gorest.co.in/public/v2/users/4295";
		String url = BASE_URL+ "/" + userID;
	
		response = ApexApacheHttpUtil.sendAndReceiveHTTPGetMSG(url);
		
		Validate.performBasicValidation(response, STATUS_CODE_200, STATUS_MESSAGE_OK);
		
		String result = ApexApacheHttpUtil.getResposeMSGFromHTTPResponse(response);
		//System.out.println("result = " + result );
		Assert.assertTrue(result.contains(name));
				
	  }
	
		  
	  //@Test(priority=2)
	  public void testWithBlankValue_Incomplete() throws ClientProtocolException, IOException {
		  HttpResponse response =null;
			String url = "https://gorest.co.in/public/v2/users/ ";
		
			response = ApexApacheHttpUtil.sendAndReceiveHTTPGetMSG(url);
	
			//validate the reponse
			Validate.performBasicValidation(response, STATUS_CODE_200, STATUS_MESSAGE_OK);			  
	  }
	  
	@Test(priority=3)
	public void testWithCorrectData() throws ClientProtocolException, IOException {
		HttpResponse response =null;
		//String url = "https://gorest.co.in/public/v2/users?name=Chandini Chattopadhyay";
		
		String url = "https://gorest.co.in/public/v2/users?name=Karunanidhi";
		
		response = ApexApacheHttpUtil.sendAndReceiveHTTPGetMSG(url);
		
		//validate the reponse
		Validate.performBasicValidation(response, STATUS_CODE_200, STATUS_MESSAGE_OK);
				
		String result = ApexApacheHttpUtil.getResposeMSGFromHTTPResponse(response);
		//System.out.println("result = " + result );
		Assert.assertTrue(result.contains("Karunanidhi"));
	
	}
	
	  
	  @Test(priority=4)
	  public void testWithCharacters() throws ClientProtocolException, IOException {
		HttpResponse response =null;
		String url = "https://gorest.co.in/public/v2/users/abcd";
	
		response = ApexApacheHttpUtil.sendAndReceiveHTTPGetMSG(url);
		//validate the reponse
		Validate.performBasicValidation(response, STATUS_CODE_404, STATUS_MESSAGE_NOT_FOUND);
		
	  }
	
	@Test(priority=6)
	public void testWithLongNumbers() throws ClientProtocolException, IOException {
		HttpResponse response =null;
		String url = "https://gorest.co.in/public/v2/users/99999999999999999999999999999999999991234";
		
		response = ApexApacheHttpUtil.sendAndReceiveHTTPGetMSG(url);
		
		//validate the response
//		System.out.println(	"\nStatusCode = " +response.getStatusLine().getStatusCode()
//		+"\nReasonPhrase = " +response.getStatusLine().getReasonPhrase()
//		);
		
		//validate the reponse
		Validate.performBasicValidation(response, STATUS_CODE_404, STATUS_MESSAGE_NOT_FOUND);  
	}
	  

	@Test(priority=5)
	public void testWithNegativeNumbers() throws ClientProtocolException, IOException {
		HttpResponse response =null;
		String url =  BASE_URL +"/-1234";
		
		response = ApexApacheHttpUtil.sendAndReceiveHTTPGetMSG(url);
		
		//validate the reponse
		
		Validate.performBasicValidation(response, STATUS_CODE_404, STATUS_MESSAGE_NOT_FOUND);
	}
	  
	@DataProvider(name="correctids_dp")
	public Object[][] getCorrectIDs() throws IOException{
		String [][] custTabData;
		String xlsFile =System.getProperty("user.dir") + "\\src\\test\\java\\com\\apex\\api_Resources\\customerData.xlsx"; 
		String sheet ="customerDetails";
		String tableMarker ="GoogleIds";
		
		XLUtils.startEndTableMarkers(xlsFile, sheet,tableMarker);
		custTabData = (String[][]) XLUtils.customerData(xlsFile, sheet);
		return custTabData;
//		String[][] data= {
//				{"6072","ShivaSimba123 New","test"},
//				{"6073","Jagan"},
//				{"6075","Karu"},
//		};
//		return data;
		
	}
}
