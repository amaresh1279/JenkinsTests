package com.qa.test;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest
{
	TestBase testBase;
	RestClient restClient;
	String URL;
	String serviceURL;
	String url;

	@BeforeClass
	public void setUp() throws Exception
	{	
		testBase = new TestBase();
		URL = testBase.prop.getProperty("URL");
		serviceURL = testBase.prop.getProperty("serviceURL");
		url = URL + serviceURL;
		System.out.println("url is :" + url);
	}
	@Test
	public void getAPITestWithoutHeaders()
	{
		restClient = new RestClient();
		CloseableHttpResponse closeableHttpResponse = null;
		try
		{
			closeableHttpResponse = restClient.getAPIWithoutHeaders(url);
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		int httpResonseCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("httpResonseCode is: " + httpResonseCode);
		Assert.assertEquals(httpResonseCode, TestBase.RESPONSE_STATUS_CODE_200);
		Header hdr = closeableHttpResponse.getFirstHeader("content-type");
		System.out.println("hdr : "+hdr.getName());
		System.out.println("hdr : "+hdr.getValue());
		System.out.println("hdr : "+hdr.getElements());
		String str = closeableHttpResponse.getFirstHeader("content-type").getValue();
		System.out.println("str : "+str);
		Header[] allHeaders = closeableHttpResponse.getAllHeaders();
		//allHeaders
	}
}
