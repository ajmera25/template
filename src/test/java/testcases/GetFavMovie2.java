package testcases;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import core.DriverManagerFactory;
import io.appium.java_client.AppiumDriver;
import model.MovieDetails;

public class GetFavMovie2{

	public static HashMap<String,MovieDetails> moviesMap = new HashMap<>();
	DriverManagerFactory dmf = new DriverManagerFactory ();
	/*@BeforeSuite
	public void beforeSuite() {
		dmf = new DriverManagerFactory();
		try {
			String platform = System.getProperty("platform");
			dmf.initializeDriver(platform);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void favMovies() throws Exception {
		String platform = System.getProperty("platform");
		dmf.initializeDriver(platform);
		moviefromYoutube(platform);
//		detailsFromImdb(platform);
	}
	
	public void moviefromYoutube(String platform) throws Exception {
		Constructor<?> constructor;
		Class<?> clazz = Class.forName("pageobjects" + "." + platform  + "." + "YoutubeHomePage");
		if(platform.equalsIgnoreCase("Desktop")) {
			constructor = clazz.getConstructor(WebDriver.class);
		}else {
			constructor = clazz.getConstructor(AppiumDriver.class);
		}
		Object c = constructor.newInstance(dmf.getDriver(platform));
		//String parameter
		Class[] paramString = new Class[1];	
		paramString[0] = String.class;
		
//		Method setUrl = clazz.getDeclaredMethod("setUrl", paramString);
//		setUrl.invoke(c,"http://www.youtube.com");
		
		Method clickLatestMovie = clazz.getDeclaredMethod("clickMovies");
		clickLatestMovie.invoke(c).toString();
		
		Method getMovieList = clazz.getDeclaredMethod("getMoviesList");
		getMovieList.invoke(c).toString();
	}
	
//	public void detailsFromImdb(String platform) throws Exception {
//		Constructor<?> constructor;
//		Class<?> imdbClass = Class.forName("pageobjects" + "." + platform  + "." + "IMDBSearchPage");
//		if(platform.equalsIgnoreCase("Desktop")) {
//			constructor = imdbClass.getConstructor(WebDriver.class);
//		}else {
//			constructor = imdbClass.getConstructor(AppiumDriver.class);
//		}
//		
//		Object imdbObject = constructor.newInstance(dmf.getDriver(platform));
//		//String parameter
//		Class[] imdbparam = new Class[1];	
//		imdbparam[0] = String.class;
//		
//		Method setImdbUrl = imdbClass.getDeclaredMethod("setUrl", imdbparam);
//		setImdbUrl.invoke(imdbObject,"http://www.imdb.com");
//		
//		Method setImdbMovieSearch = imdbClass.getDeclaredMethod("searchMovieNameandGetImdbDirectorAndRating");
//		setImdbMovieSearch.invoke(imdbObject);
//		dmf.closeAllDriver();
//		
//	}
	
	/*@AfterSuite
	public void afterSuite() throws Exception {
	 	try{
	 		if(dmf.getDesktopWebDriver()!=null)
	 		dmf.getDesktopWebDriver().quit();
	 		if(dmf.getAppiumDriver()!=null)
	 		dmf.getAppiumDriver().quit();
	        
		}catch(Exception e){
			Reporter.log("Error in Initializing the test", true);
			e.printStackTrace();
		}
	}*/
}
