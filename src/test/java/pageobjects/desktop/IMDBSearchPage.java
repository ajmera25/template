package pageobjects.desktop;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;
import model.MovieDetails;
import testcases.GetFavMovie;

public class IMDBSearchPage extends BasePage{

	public IMDBSearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name="q")
	private WebElement searchMovieName;
	
	
	public boolean setUrl(String url) throws Exception{
		boolean bval = false;
		try{
			pageWebDriverClient.setURL(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}
	
	public boolean searchMovieNameandGetImdbDirectorAndRating() throws Exception{
		boolean bval = false;
		MovieDetails movieDetail = new MovieDetails();
		try{
			for(String movieName : GetFavMovie.moviesMap.keySet()) {
				pageWebDriverClient.setText(searchMovieName,movieName);
				pageWebDriverClient.click("//div[@class='suggestionlabel']/span[@title='"+movieName+"']");
				movieDetail.setImdbRating(pageWebDriverClient.getText("//div[@class='imdbRating']//span"));
				movieDetail.setImdbDirectorName(pageWebDriverClient.getText(".//h4[text()='Director:']/following-sibling::a"));
				 GetFavMovie.moviesMap.put(movieName, movieDetail);
		}}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}
	
	/*public boolean getImdbRating(String movieName) throws Exception{
		boolean bval = false;
		try{
			pageWebDriverClient.setText(searchMovieName,movieName);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}
	
	public boolean getDirector(String movieName) throws Exception{
		boolean bval = false;
		try{
			pageWebDriverClient.setText(searchMovieName,movieName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}*/
}
