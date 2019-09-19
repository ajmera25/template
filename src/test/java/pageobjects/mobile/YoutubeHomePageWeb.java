package pageobjects.mobile;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;

public class YoutubeHomePageWeb extends BasePage{

	@FindBy(xpath = "//a[contains(@href, 'trending')]")
	WebElement mlnk_Trending;
	
	@FindBy(xpath = "//a[contains(@aria-label, 'Movies')]")
	WebElement mlnk_Movies;
	
	public YoutubeHomePageWeb(WebDriver driver) {
		super(driver);
	}
	
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
			String moviesLink = "//span[text()='Movies']";
			String viewAllLink = "//paper-button[@aria-label='View all']";
			pageWebDriverClient.waitForElementToBeClickable(moviesLink);
			pageWebDriverClient.click(moviesLink);
			pageWebDriverClient.waitForElementToBeClickable(viewAllLink);
			pageWebDriverClient.click(viewAllLink);
			bval = pageWebDriverClient.waitForVisibilityThenCheckIsWebElementDisplayed("//span[text()='New Releases']");
		}catch(Exception e){
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