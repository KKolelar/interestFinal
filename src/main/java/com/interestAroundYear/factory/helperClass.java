package com.interestAroundYear.factory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
 
public class helperClass {
 
static WebDriver driver;
static Properties p;

 
	public  static WebDriver initializeBrowser(String browser) throws IOException
	{
		//code to initialize the browser
		if(getProperties().getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//os
			if (getProperties().getProperty("os").equalsIgnoreCase("windows")) {
			    capabilities.setPlatform(Platform.WIN11);
			} else if (getProperties().getProperty("os").equalsIgnoreCase("mac")) {
			    capabilities.setPlatform(Platform.MAC);
			} else {
			    System.out.println("No matching OS..");
			      }
			//browser getProperties().getProperty("browser")
			switch (getProperties().getProperty("browser").toLowerCase()) {
			    case "chrome":
			        capabilities.setBrowserName("chrome");
			        break;
			    case "edge":
			        capabilities.setBrowserName("MicrosoftEdge");
			        break;
			    default:
			        System.out.println("No matching browser");
			     }
	        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		else if(getProperties().getProperty("execution_env").equalsIgnoreCase("local"))
			{
				switch(getProperties().getProperty("browser").toLowerCase()) 
				{
				case "chrome":
			        driver=new ChromeDriver();
			        break;
			    case "edge":
			    	driver=new EdgeDriver();
			        break;
			    default:
			        System.out.println("No matching browser");
			        driver=null;
				}
			}
		 driver.manage().deleteAllCookies(); 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 return driver;					   //returning the driver
	}
	
	
	public static WebDriver getDriver() {
		return driver;                    //returning the driver
	}
 
    public static Properties getProperties() 
    {	
    	try {
    		
    		//creating a FileReader object to read config file
    		FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");      //path of config.properties file
    		p=new Properties();			  //Creating an object for Properties
    		p.load(file);				  //Loading the properties from configuration file
    	}
    	catch(Exception e) {
		    //catch the exception that occur during file reading and loading properties
    	}
    	return p;                        //returning the properties object containing the loaded properties
    }
    
    public static String decode(String s) {
    	byte[] actualByte = Base64.getDecoder().decode(s);	//decoding the encoded string
		String actualString = new String(actualByte);		
		return actualString;								//returning the actual string after decoding
    }
}