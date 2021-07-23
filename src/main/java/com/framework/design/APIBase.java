package com.framework.design;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class APIBase extends Reporter{
	
	public static String country, countryCode;
	@SuppressWarnings("rawtypes")
	public static ResponseBody finalResponse;
	
	public void setBASEURL() {
		Properties prop = new Properties();
		setNode();
		try {
			prop.load(new FileInputStream(".\\src\\test\\resources\\config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			RestAssured.baseURI = prop.getProperty("BASEURL");
			reportStep("Base URL " +   prop.getProperty("BASEURL") + " is successfully set", "pass");
		} catch (Exception e) {
			reportStep("Unable to set the Base URL " +   prop.getProperty("BASEURL") , "fail");
		}
	}
	
	public String getCountryCode() throws FileNotFoundException, IOException{
		CSVReader read = new CSVReader();
		if (read.readCSV(country)!= null)
		{
			reportStep("Country code for " + country + " is retrieved as " + read.readCSV(country), "pass");
			return read.readCSV(country);
		}
		else
			reportStep("Unable to get the country code " , "fail");
			return null;	
	}
		
	
	@SuppressWarnings("rawtypes")
	public ResponseBody getAndvalidateResponse(String resource)
	{
		
		Response response = RestAssured.given().header("Content-Type", "application/json").get(resource);
		if(response.statusCode()<300) {
			reportStep("Postal/zip code information is successfully retrieved", "pass");
		}
		else
		{
			reportStep("Unable to retrieve the postal/zip code information", "fail");
		}
		return response.getBody();
	}
	
	public void checkBody() {
		if(finalResponse.asString().equals(null)) {
			reportStep("Unable to retrieve the information", "fail");
		}
		else
		{
			reportStep("Validation successfull !! " +finalResponse.asString(), "pass");
		}
	}

}



