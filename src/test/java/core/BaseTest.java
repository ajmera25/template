package core;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

	@BeforeSuite
	public void beforeSuite() {
		
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
