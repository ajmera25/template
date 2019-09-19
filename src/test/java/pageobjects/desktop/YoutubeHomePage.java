package pageobjects.desktop;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import core.BasePage;
import model.MovieDetails;
import testcases.GetFavMovie;

public class YoutubeHomePage extends BasePage{

	public YoutubeHomePage(WebDriver driver) {
		super(driver);
	}
	
	public void setUrl(String url) throws Exception{
		try{
			pageWebDriverClient.setURL("https://www.youtube.com");
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
			pageWebDriverClient.setPageLoadTimeout(10);
			bval = pageWebDriverClient.click(viewAllLink);
			pageWebDriverClient.resetPageLoadTimeout();
		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}
	
	public HashMap<String, String> getMoviesList () {
		HashMap<String, String> moviesList = new HashMap<String, String>();
		String movieTitle = "";
		String moviePrice = "";
		String movie = "//ytd-grid-movie-renderer";
		MovieDetails movieDetails = new MovieDetails();
		try{
			
			for (int i=1; i<=20; i++) {
				movieTitle = pageWebDriverClient.getText(movie+"["+i+"]//span[@id='video-title']");
				System.out.println("movieTitle get text >> " + movieTitle);
				moviePrice = pageWebDriverClient.getText(movie+"["+i+"]//span[contains(text(),'From')]");
				if(!movieTitle.isEmpty() && !moviePrice.isEmpty()) {
					moviePrice = moviePrice.split("\\u20B9")[1];
					System.out.println("movieTitle >> " + movieTitle);
					System.out.println("moviePrice >> " + moviePrice);
					moviesList.put(movieTitle, moviePrice);
					movieDetails.setMovieName(moviePrice);
					GetFavMovie.moviesMap.put(movieTitle,movieDetails);
				}
				
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(moviesList);
		return moviesList;
	}

}
