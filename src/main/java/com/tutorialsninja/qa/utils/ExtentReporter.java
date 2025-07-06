package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReports = new ExtentReports();
		
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Results Report");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
		
		extentReports.attachReporter(sparkReporter);
		
		Properties configProperties = new Properties();
		File configPropertiesFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		try {
		FileInputStream fis = new FileInputStream(configPropertiesFile);
		configProperties.load(fis);
		} catch(Throwable e) {
			
			e.printStackTrace();
			
		}
		
		extentReports.setSystemInfo("Appliaction URL", configProperties.getProperty("url"));
		extentReports.setSystemInfo("Browser Name", configProperties.getProperty("browserName"));
		extentReports.setSystemInfo("Email", configProperties.getProperty("validEmail"));
		extentReports.setSystemInfo("Password", configProperties.getProperty("validPassword"));
		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReports.setSystemInfo("Username", System.getProperty("user.name")); // Who is running the machine
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReports;
	}

}
