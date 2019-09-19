package pageobjects.mobile;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;
import io.appium.java_client.AppiumDriver;

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
	
	String str_MoviesList = "com.google.android.youtube:id/title";
	
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
/*			String moviesLink = "//span[text()='Movies']";
			String viewAllLink = "//paper-button[@aria-label='View all']";
			pageWebDriverClient.waitForElementToBeClickable(moviesLink);
			pageWebDriverClient.click(moviesLink);
			pageWebDriverClient.waitForElementToBeClickable(viewAllLink);
			pageWebDriverClient.click(viewAllLink);
			bval = pageWebDriverClient.waitForVisibilityThenCheckIsWebElementDisplayed("//span[text()='New Releases']");
*/		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}
	
	public HashMap<String, String> getMoviesList () {
		HashMap<String, String> moviesList = new HashMap<String, String>();
		String movieTitle = "//span[@id='video-title']";
		String movePrice = "//span[contains(text(),'From')]";
		String movie = "//ytd-grid-movie-renderer";
		try{
			
			for (int i=1; i<=20; i++) {
				moviesList.put(pageWebDriverClient.getText(movie+"["+i+"]"+movieTitle),pageWebDriverClient.getText(movie+"["+i+"]"+movePrice));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return moviesList;
	}

}
