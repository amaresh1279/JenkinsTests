package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest
{
	TestBase testBase;
	RestClient restClient;
	Users users;
	CloseableHttpResponse closeableHttpResponse;
	JSONObject jsonObject;
	String URL;
	String serviceURL;
	String url;
	
	@BeforeMethod
	public void setUp()
	{
		testBase = new TestBase();
		URL = testBase.prop.getProperty("URL");
		serviceURL = testBase.prop.getProperty("serviceURL");
		url = URL + serviceURL;
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException
	{
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//jackson API
		ObjectMapper objectMapper = new ObjectMapper();
		users = new Users("somename", "somejob");

		//java object to json file
		objectMapper.writeValue(new File("/C:/Users/Akumar/eclipse-workspace/RestApiTest/src/main/java/com/qa/data/users.json"), users);
		
		//object to json in string
		String usersJsonString = objectMapper.writeValueAsString(users);
		System.out.println("user json in string is: " + usersJsonString);
		
		closeableHttpResponse = restClient.postAPITest(url, usersJsonString, headerMap);
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
	}
}
