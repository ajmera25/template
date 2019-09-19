package pageobjects.mobile;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;

public class YoutubeHomePage extends BasePage{

	public YoutubeHomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//android.widget.Button[@content-desc='Trending']/android.widget.TextView")
	WebElement mlnk_Trending;
	
	@FindBy(xpath = "//android.widget.ImageView[@content-desc='Movies']")
	WebElement mlnk_Movies;
	
	@FindBy(id = "com.google.android.youtube:id/action_button")
	WebElement mlnk_GetMovies;
	
	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"
			+ "/android.widget.FrameLayout/android.widget.LinearLayout"
			+ "/android.widget.FrameLayout/android.widget.FrameLayout"
			+ "/android.view.ViewGroup/android.widget.FrameLayout[2]"
			+ "/android.view.ViewGroup/android.view.ViewGroup"
			+ "/android.widget.FrameLayout[2]/android.widget.FrameLayout"
			+ "/androidx.viewpager.widget.ViewPager/android.view.ViewGroup"
			+ "/android.support.v7.widget.RecyclerView/android.widget."
			+ "LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView")
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
			mlnk_Trending.click();
			mlnk_Movies.click();
			
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
