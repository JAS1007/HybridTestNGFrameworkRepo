package com.tutorialsninja.qa.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class BaseClass {
	
	WebDriver driver;
	public Properties configProp;
	public Properties testDataProp;
	
	public BaseClass() {
		
		try {
			
			testDataProp = new Properties();
			FileInputStream testDataFis = new FileInputStream("./src/main/java/com/tutorialsninja/qa/testdata/testdata.properties");
			testDataProp.load(testDataFis);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		try {
			
			configProp = new Properties();
			FileInputStream configFis = new FileInputStream("./src/main/java/com/tutorialsninja/qa/config/config.properties");
			configProp.load(configFis);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
	
	}
	
	public WebDriver openTheBrowserAndNavigateToTheWebsiteURL(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			driver = new ChromeDriver();
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();	
			
		} else if (browserName.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
			
		} else if (browserName.equalsIgnoreCase("safari")) {
			
			driver = new SafariDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(configProp.getProperty("url"));
		
		return driver;
		
	}

}
