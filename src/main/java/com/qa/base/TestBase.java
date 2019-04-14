package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase
{
	public Properties prop;
	public static final int RESPONSE_STATUS_CODE_200 = 200;
	public static final int RESPONSE_STATUS_CODE_201 = 201;
	public static final int RESPONSE_STATUS_CODE_202 = 202;
	public static final int RESPONSE_STATUS_CODE_400 = 400;
	public static final int RESPONSE_STATUS_CODE_401 = 401;

	public TestBase()
	{
		try
		{
			prop = new Properties();
			FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/qa/config/config.properties");
			prop.load(fileInputStream);
		}
		catch (FileNotFoundException FNFE)
		{
			FNFE.printStackTrace();
		}
		catch (IOException IOE)
		{
			IOE.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}