package com.interestAroundYear.stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.interestAroundYear.factory.helperClass;

import io.cucumber.java.*;


public class Hooks {

	//static variables to hold Webdriver instance and properties
	static WebDriver driver;             
	static Properties p;
	//This method runs before all scenarios
	@BeforeAll
	//@Parameters({"browser"}) 
	public static void setup(String browser) throws IOException {
		driver = helperClass.initializeBrowser(browser);         //calling the initilizeBrowser method from BaseClass and storing it in driver or Initialize webdriver instance
		p = helperClass.getProperties();       		   //calling the getProperties method from BaseClass and storing it in "p"   or Get properties for configuration      
		driver.manage().window().maximize();		   //maximize the browser window
		driver.get(p.getProperty("appURL"));		   //Navigate to application URL "be.cognizant" 

	}

	
	//This method runs after all scenarios
	@AfterAll
	public static void tearDown() {

		driver.quit();                              //closing all the browser windows

	}

	
	//This method runs after each step in a scenario
	@AfterStep
	public void addScreenshot(Scenario scenario) {
		driver=helperClass.getDriver();
		// this is for cucumber junit report
		if (!scenario.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());			//attach the screenshot to the scenario

		}

	}

}
