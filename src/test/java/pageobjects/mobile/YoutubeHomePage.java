package pageobjects.mobile;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.tools.ant.taskdefs.Sync.MyCopy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

public class YoutubeHomePage extends BasePage{

	public YoutubeHomePage(AppiumDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//android.widget.Button[@content-desc='Trending']")
	MobileElement mlnk_Trending;
	
	@FindBy(xpath = "//android.widget.ImageView[@content-desc='Movies']")
	MobileElement mlnk_Movies;
	
	@FindBy(xpath="//android.widget.TextView[contains(@resource-id,'action_button') and @text='GET MOVIES']")
	MobileElement mlnk_GetMovies;
	
	@FindBy(xpath="//android.widget.TextView[contains(@resource-id,'card_list_button') and @text='VIEW ALL']")
	MobileElement mlnk_ViewAll;
	
	String str_MoviesList = "//android.widget.GridLayout[contains(@resource-id,'compact_movie_item')]";
	
	@FindBy(id = "'com.google.android.youtube:id/title']")
	MobileElement mlnk_ListOfMovies;
	
	
	
	public void setUrl(String url) throws Exception{
		try{
			mobileWebDriverClient.setURL(url);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean clickMovies() throws Exception{
		boolean bval = false;
		try{
			Thread.sleep(15000);
			mobileWebDriverClient.click(mlnk_Trending);
			mobileWebDriverClient.click(mlnk_Movies);
			mobileWebDriverClient.click(mlnk_GetMovies);
			mobileWebDriverClient.click(mlnk_ViewAll);
		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}
	
	public HashMap<String, String> getMoviesList () {
		HashMap<String, String> moviesList = new HashMap<String, String>();
		try{
		List<MobileElement> mobElements = appiumDriver.findElementsByXPath("//android.widget.GridLayout[contains(@resource-id,'compact_movie_item')]");
		int i = 1;
		String strName = null, strPrice = null;
		for(int j=0;j<mobElements.size();j++){
			Thread.sleep(1000);
			MobileElement mobelement = mobElements.get(j);
//			if(!mobileWebDriverClient.isMobileElementDisplayed(mobelement)) {
//				TouchAction action = new TouchAction(appiumDriver);
//				action.press(pressOptions)
//				action.scroll(mobelement, 0, 100);
//				action.perform();
//			}
//				mobileWebDriverClient.scrollToExact(mobelement);
			MobileElement title = mobelement.findElementByXPath("//android.widget.TextView[contains(@resource-id,'title')]");
			strName = title.getText();
			System.out.println("Movie name "+strName);
			MobileElement text = mobelement.findElementByXPath("//android.widget.TextView[contains(@resource-id,'ypc_badge_text')]");
//			if(!mobileWebDriverClient.isMobileElementDisplayed(text)) {
				
//			}
			strPrice=text.getText();
			System.out.println("Price "+strPrice);
			moviesList.put(strName, strPrice);
			
			if(i == 20){
				break;
			}
			i++;
			mobileWebDriverClient.scroll();
		}	
		System.out.println(moviesList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return moviesList;
	}

}
