package core;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	
	public WebDriver driver;
	DriverManagerFactory DMF = new DriverManagerFactory();
	@BeforeSuite
	public void beforeSuite() {
		DriverManagerFactory DMF = new DriverManagerFactory();
		try {
			String platform = System.getProperty("platform");
			DMF.initializeDriver(platform);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void afterSuite() throws Exception {
		DriverManagerFactory dmf = new DriverManagerFactory();
	 	try{
	 		if(dmf.getDesktopWebDriver()!=null)
	 		dmf.getDesktopWebDriver().quit();
	 		if(dmf.getAppiumDriver()!=null)
	 		dmf.getAppiumDriver().quit();
	        
		}catch(Exception e){
			Reporter.log("Error in Initializing the test", true);
			e.printStackTrace();
		}
	}
}
