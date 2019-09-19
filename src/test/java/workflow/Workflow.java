package workflow;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;

import core.DriverManagerFactory;
import io.appium.java_client.AppiumDriver;

public class Workflow {

	public void workflowMethod() throws Exception {
		Constructor<?> constructor;
		DriverManagerFactory DMF = new DriverManagerFactory();
		String platform = System.getProperty("platform");
		
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
		
		Method setUrl = clazz.getDeclaredMethod("url", paramString);
		setUrl.invoke(c,"http://www.youtube.com");
		
		Method clickLatestMovie = clazz.getDeclaredMethod("clickMovie");
		clickLatestMovie.invoke(c).toString();
		
		Method getMovieList = clazz.getDeclaredMethod("getMovieList");
		getMovieList.invoke(c).toString();
		
	}}