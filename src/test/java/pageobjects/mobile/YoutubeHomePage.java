package pageobjects.mobile;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class YoutubeHomePage extends BasePage{

	public YoutubeHomePage(AppiumDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//android.widget.Button[@content-desc='Trending']")
	WebElement mlnk_Trending;
	
	@FindBy(xpath = "//android.widget.ImageView[@content-desc='Movies']")
	WebElement mlnk_Movies;
	
	@FindBy(xpath="//android.widget.TextView[contains(@resource-id,'action_button') and @text='GET MOVIES']")
	WebElement mlnk_GetMovies;
	
	@FindBy(xpath="//android.widget.TextView[contains(@resource-id,'card_list_button') and @text='VIEW ALL']")
	WebElement mlnk_ViewAll;
	
	String str_MoviesList = "//android.widget.GridLayout[contains(@resource-id,'compact_movie_item')']";
	
	@FindBy(id = "'com.google.android.youtube:id/title']")
	WebElement mlnk_ListOfMovies;
	
	
	
	public void setUrl(String url) throws Exception{
		try{
			pageWebDriverClient.setURL(url);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean clickMovies() throws Exception{
		boolean bval = false;
		try{
			Thread.sleep(5000);
			mlnk_Trending.click();
			mlnk_Movies.click();
			mlnk_GetMovies.click();
			mlnk_ViewAll.click();
		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}
	
	public HashMap<String, String> getMoviesList () {
		HashMap<String, String> moviesList = new HashMap<String, String>();
		try{
		@SuppressWarnings("unchecked")
		List<MobileElement> mobElements = appiumDriver.findElements(By.xpath(str_MoviesList));
		int i = 1;
		String strName = null, strPrice = null;
		for(MobileElement mobelement : mobElements){
			mobelement.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'title')")).getText();
			mobelement.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'ypc_badge_text')]")).getText();
			moviesList.put(strName, strPrice);
			if(i == 20){
				break;
			}
			i++;
		}	
		System.out.println(moviesList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return moviesList;
	}

}