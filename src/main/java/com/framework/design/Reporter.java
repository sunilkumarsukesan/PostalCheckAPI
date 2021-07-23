package com.framework.design;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.Scenario;

public class Reporter {
	public static String testcaseName;
	public static String testDescription;
	public static String fileName = "report.html";
	public static String testName,folderName = "";
	static ExtentReports extent;
	static ExtentTest parentTest,test;
	static Scenario scenario;
	
	public static void startReport() {
		String date = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss").format(new Date());
		folderName = "reports/" + date;

		File folder = new File("./" + folderName);
		if (!folder.exists()) {
			folder.mkdir();
		}
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("./" + folderName + "/" + fileName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	public static void startTestCase() {
		ExtentTest parent = extent.createTest("Postal Zip code Validation", testDescription);
		parentTest = parent;
		testName=testcaseName;
	}
	
	public static void setNode() {
		ExtentTest child = parentTest.createNode(testName);
		test = child;
	}
	
	public static void reportStep(String desc, String status) {
			if (status.equalsIgnoreCase("pass")) {
				test.pass(desc);
			} else if (status.equalsIgnoreCase("fail")) {
				test.fail(desc);
				throw new RuntimeException("See the reporter for details.");
			} else if (status.equalsIgnoreCase("warning")) {
				test.warning(desc);
			} else if (status.equalsIgnoreCase("skipped")) {
				test.skip("The test is skipped due to dependency failure");
			} else if (status.equalsIgnoreCase("INFO")) {
				test.info(desc);
			}
	}
	
	public static void endResult() {
		extent.flush();
	}


}
