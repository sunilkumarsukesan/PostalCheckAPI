package com.framework.steps;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.framework.design.APIBase;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ZipPostalAPIsteps extends APIBase{
	Scenario scenario;
	
	@Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

	@Given ("As a user i have valid Base URL")
	public void setBaseURL() {
		testName = scenario.getName();
		setBASEURL();
	}
		
	@And ("I set the country as {string}")
	public void setCountry(String getCountry) {	
		country = getCountry;
		if (!(getCountry.equalsIgnoreCase("great britain") || getCountry.equalsIgnoreCase("united states")))
			{
			reportStep("Cannot validate for countries other than Great Britain/United States", "fail");
			}
			
	}
	
	@And ("I retrieve the country code from the CSV file")
	public void retrieveCountryCode() throws FileNotFoundException, IOException {
		countryCode = getCountryCode();
	}
	
	@When ("I send the postal code {string} along with country code")
	public void getInformation(String postcode) {
		finalResponse = getAndvalidateResponse("/" + countryCode +"/" + postcode);
	}
	
	
	@Then ("I should be able to get the postal or zip code information")
	public void showInformation() {
		checkBody();
	}

}
