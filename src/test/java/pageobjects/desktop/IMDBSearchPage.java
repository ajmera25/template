package pageobjects.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;

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
	
	public boolean searchMovieName(String movieName) throws Exception{
		boolean bval = false;
		try{
			pageWebDriverClient.setText(searchMovieName,movieName);
			pageWebDriverClient.click("//div[@class='suggestionlabel']/span[@title='"+movieName+"']");
		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}
	
	public boolean getImdbRating(String movieName) throws Exception{
		boolean bval = false;
		try{
			pageWebDriverClient.setText(searchMovieName,movieName);
			pageWebDriverClient.getText("//div[@class='imdbRating']//span");
		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}
	
	public boolean getDirector(String movieName) throws Exception{
		boolean bval = false;
		try{
			pageWebDriverClient.setText(searchMovieName,movieName);
			pageWebDriverClient.getText(".//h4[text()='Director:']/following-sibling::a");
		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}
}
