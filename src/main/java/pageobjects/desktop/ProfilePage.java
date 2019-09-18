package pageobjects.desktop;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.BasePage;

public class ProfilePage extends BasePage{
	
	public ProfilePage(WebDriver driver) {
		super(driver);
	}
	
	public String getMaxRetweetedAndLikedTweet() throws Exception {
		String tweetId ="";
		String maxTweetId ="";
		int maxCount = 0;
		int count = 1;
		String forumTweets = "//a[contains(@title,'Sep')]";
		String retweetCountXpath = "//div[@data-testid='retweet']//span[text()]";
		String likeCountXpath = "//div[@data-testid='like']//span[text()]";
		try {
			pageWebDriverClient.waitForVisibilityOfElementLocatedBy("//span[text()='@stepin_forum']");
			
			JavascriptExecutor jse = (JavascriptExecutor)pageWebDriverClient.getWebDriver();
			List<WebElement> elementList = new ArrayList<WebElement>();
			List<String> tweetIdList = new ArrayList<String>();
			
			String javascript = "window.scrollTo(0,";
			jse.executeScript(javascript + "0)");
			while(pageWebDriverClient.findElements("//article//a[contains(@title,'Aug')]").size() < 1 && count < 20) {
				elementList = pageWebDriverClient.findElements(forumTweets);
				for(WebElement element : elementList) {
					tweetId = element.getAttribute("href");
					tweetId = tweetId.substring(tweetId.lastIndexOf("/")+1);
					if(!tweetIdList.contains(tweetId)) {
						tweetIdList.add(tweetId);
						retweetCountXpath = "//a[contains(@href,'"+tweetId+"')]/ancestor::article//div[@data-testid='retweet']//span[text()]";
						likeCountXpath = "//a[contains(@href,'"+tweetId+"')]/ancestor::article//div[@data-testid='like']//span[text()]";
						String tweetCount = "0";
						String likeCount = "0";
						if(pageWebDriverClient.findElements(retweetCountXpath).size() > 0) {
							tweetCount = pageWebDriverClient.getText(retweetCountXpath);
						}
						if(pageWebDriverClient.findElements(retweetCountXpath).size() > 0) {
							likeCount = pageWebDriverClient.getText(likeCountXpath);
						}
						
						int totalCount = Integer.parseInt(tweetCount) + Integer.parseInt(likeCount);
						if(maxCount < totalCount) {
							maxCount = totalCount;
							maxTweetId = tweetId;
						}
					}
					
				}
				
				jse.executeScript(javascript + (1000*count) + ")");
				System.out.println(count);
				count++;
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return maxTweetId;
	}
	
	public boolean clickTweet() throws Exception {
		boolean bval = false;
		try {
			String tweetId = getMaxRetweetedAndLikedTweet();
			//String tweetId = "1171283679940730880";
			pageWebDriverClient.scrollWindowVerticallyToClickableElement("//a[contains(@href,'"+tweetId+"')]");
			bval = pageWebDriverClient.click("//a[contains(@href,'"+tweetId+"')]");
			pageWebDriverClient.waitForVisibilityOfElementLocatedBy("//a[contains(@href, 'retweets')]");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bval;
	}
	
}
