package pageobjects.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;
import utilities.TestFrameworkException;

public class YoutubeHomePage extends BasePage{

	public YoutubeHomePage(WebDriver driver) {
		super(driver);
	}

	public boolean clickMovies() throws Exception{
		boolean bval = false;
		try{
			String moviesLink = "//span[text()='Movies']";
			pageWebDriverClient.waitForElementToBeClickable(moviesLink);
			pageWebDriverClient.click(moviesLink);
			bval = pageWebDriverClient.waitForVisibilityThenCheckIsWebElementDisplayed("//span[text()='New Releases']");
		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}
	
	public boolean getMoviesList () {
		boolean bval = false;
		try{
			String moviesTitle = "//span[@id='video-title']";
		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}

}
