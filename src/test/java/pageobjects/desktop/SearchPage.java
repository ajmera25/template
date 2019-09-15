package pageobjects.desktop;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import core.BasePage;
import utilities.TestFrameworkException;

public class SearchPage extends BasePage{

	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Search Twitter']")
	WebElement txt_SearchTweet;
	
	String strHandleLink = "//span[text()='%s']";
	
	public void searchTweet(String strTweet) throws TestFrameworkException{
		try{
			pageWebDriverClient.clearTextAndType(txt_SearchTweet, strTweet);
			if(strTweet.startsWith("@")){ //to search handle
				if(pageWebDriverClient.isWebElementDisplayed(strHandleLink.replace("%s", strTweet))){
					pageWebDriverClient.click(strHandleLink.replace("%s", strTweet));
				}else{
					Reporter.log("Handle name is not visible, showing all related tweet", true);
					pageWebDriverClient.sendEnterKey();
				}
			}else{ //to search tweet
				pageWebDriverClient.sendEnterKey();
			}
		}catch(Exception e){
			throw new TestFrameworkException("Unable to Login Successfully", e);
		}
	}
}
