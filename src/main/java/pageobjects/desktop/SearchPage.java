package pageobjects.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;

public class SearchPage extends BasePage{

	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Search Twitter']")
	WebElement txt_SearchTweet;
	
	
	public boolean searchTweet(String strTweet) throws Exception{
		boolean bval = false;
		try{
			String strHandleLink = "//span[text()='"+strTweet+"']";
			pageWebDriverClient.waitForElementToBeClickable(txt_SearchTweet);
			pageWebDriverClient.clearTextAndType(txt_SearchTweet, strTweet);
			pageWebDriverClient.waitForElementToBeClickable(strHandleLink);
			bval = pageWebDriverClient.click(strHandleLink);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bval;
	}
}
