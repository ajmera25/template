package testcases;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import core.DriverManagerFactory;
import io.appium.java_client.AppiumDriver;
import workflow.Workflow;

public class GetFavMovie{
	
	@Test
	public void method1() throws Exception {
		Workflow wf = new Workflow();
		Constructor<?> constructor;
		DriverManagerFactory DMF = new DriverManagerFactory();
		String platform = System.getProperty("platform");
		DMF.initializeDriver(platform);
		Class<?> clazz = Class.forName("pageobjects" + "." + platform  + "." + "YoutubeHomePage");
		if(platform.equalsIgnoreCase("Desktop")) {
			constructor = clazz.getConstructor(WebDriver.class);
		}else {
			constructor = clazz.getConstructor(AppiumDriver.class);
		}
		Object c = constructor.newInstance(DMF.getDriver(platform));
		//String parameter
		Class[] paramString = new Class[1];	
		paramString[0] = String.class;
		
		Method setUrl = clazz.getDeclaredMethod("setUrl", paramString);
		setUrl.invoke(c,"http://www.youtube.com");
		
		Method clickLatestMovie = clazz.getDeclaredMethod("clickMovies");
		clickLatestMovie.invoke(c).toString();
		
		Method getMovieList = clazz.getDeclaredMethod("getMovieList");
		getMovieList.invoke(c).toString();
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
