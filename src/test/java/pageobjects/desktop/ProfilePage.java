package pageobjects.desktop;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.BasePage;
import utilities.TestFrameworkException;

public class ProfilePage extends BasePage{
	
	public ProfilePage(WebDriver driver) {
		super(driver);
	}
	
	public String getMaxRetweetedAndLikedTweet() throws Exception {
		String tweetId ="";
		int maxCount = 0;
		String forumTweets = "//small[@class='time']/a[contains(@title,'Aug')]/../../../parent::div[@data-screen-name='stepin_forum']";
		String retweetCountXpath = "//div[contains(@class,'ProfileTweet-action--retweet')]/button[@aria-describedby]/span/span";
		String likeCountXpath = "//div[contains(@class,'ProfileTweet-action--favorite')]/button[@aria-describedby]/span/span";
		try {
			List<WebElement> elementList = pageWebDriverClient.findElements(forumTweets);
			for(WebElement element : elementList) {
				String tweetCount = pageWebDriverClient.getText(element.findElement(By.xpath(retweetCountXpath)));
				String likeCount = pageWebDriverClient.getText(element.findElement(By.xpath(likeCountXpath)));
				int totalCount = Integer.parseInt(tweetCount) + Integer.parseInt(likeCount);
				if(maxCount < totalCount) {
					maxCount = totalCount;
					tweetId = pageWebDriverClient.getAttribute(forumTweets+"/parent::li", "id");
				}
			}
		}catch (Exception ex) {
			throw new TestFrameworkException("Unable to get tweet with max retweets and likes", ex); 
		}
		return tweetId;
	}
	
	public void clickTweet() throws Exception {
		String tweetId = getMaxRetweetedAndLikedTweet();
		pageWebDriverClient.click("//li[@id='"+tweetId+"']");
	}
	
}
