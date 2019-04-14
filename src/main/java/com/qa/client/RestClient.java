package com.qa.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;

public class RestClient
{
	TestBase testBase;
	
	public CloseableHttpResponse getAPIWithoutHeaders(String url) throws ClientProtocolException, IOException
	{
		testBase = new TestBase();
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
		return closeableHttpResponse;
	}
	
	public CloseableHttpResponse getAPIWithHeaders(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException
	{
		testBase = new TestBase();
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		for (Map.Entry<String, String> entry : headerMap.entrySet())
		{
			httpGet.addHeader(entry.getKey(), entry.getValue());
	    }
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
		return closeableHttpResponse;
	}
	
	public CloseableHttpResponse postAPITest(String url, String stringEntity, HashMap<String, String> headerMap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(stringEntity));
		
		for (Map.Entry<String, String> entry : headerMap.entrySet())
		{
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
		return closeableHttpResponse;
	}
}
