package com.framework.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.framework.design.Reporter;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(features = {"src/test/java/com/framework/features" }, glue = {
		"com.framework.steps" }, monochrome = true)

@RunWith(Cucumber.class)
public class TestRunner extends Reporter{
	
	@BeforeClass
	public static void setup() {     
		startReport();
		startTestCase();
	}

	@AfterClass
	public static void tearDown() {
		endResult();
	}
	
}
