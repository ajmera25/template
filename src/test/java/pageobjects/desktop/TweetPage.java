package pageobjects.desktop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;
import utilities.TestFrameworkException;

public class TweetPage extends BasePage{

	public TweetPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//a[contains(@href, 'retweets')]")
	WebElement lnk_Retweets;
	
	@FindBy(xpath="//a[contains(@href, 'likes')]")
	WebElement lnk_Likes;
	
	String strUsersCell = "//section//div[@data-testid = 'UserCell']";
	String strFollowLink = "//a[contains(@href, 'follow')]";
	
	@FindBy(xpath="//a[contains(@href, 'following')]/span/span")
	WebElement lbl_FollowingCount;
	
	@FindBy(xpath="//a[contains(@href, 'follower')]/span/span")
	WebElement lbl_FollowerCount;
		
	String strBioText = "(//span[text() = '%s']/ancestor::div[@data-testid = 'UserCell']//div/span)[last()]";
		
	public List<String> getListOfHandleOfUsersWhoLiked(){
		List<String> handlesName = new ArrayList<String>();
		try{
			pageWebDriverClient.click(lnk_Likes);
			pageWebDriverClient.waitForVisibilityOfElementLocatedBy(strUsersCell);
			handlesName = getListOfHandleOfUsers();
		}catch(Exception e){
			try {
				throw new TestFrameworkException("Unable to fetch list", e);
			} catch (TestFrameworkException ex) {
				ex.printStackTrace();
			}
		}	
		return handlesName ;
	}
	
	public List<String> getListOfHandleOfUsersWhoRetweeted(){
		List<String> handlesName = new ArrayList<String>();
		try{
			pageWebDriverClient.click(lnk_Retweets);
			pageWebDriverClient.waitForVisibilityOfElementLocatedBy(strUsersCell);
			handlesName = getListOfHandleOfUsers();
		}catch(Exception e){
			try {
				throw new TestFrameworkException("Unable to fetch list", e);
			} catch (TestFrameworkException ex) {
				ex.printStackTrace();
			}
		}	
		return handlesName ;
	}
	
	public List<String> getListOfHandleOfUsers(){
		String handleNames = strUsersCell + "//div[@dir='ltr']";
		List<String> handlesName = new ArrayList<String>();
		try{
			List<WebElement> handlesInfo = pageWebDriverClient.findElements(handleNames);
			for(WebElement handle: handlesInfo){
				handlesName.add(handle.getText());
			}
		}catch(Exception e){
			try {
				throw new TestFrameworkException("Unable to fetch list", e);
			} catch (TestFrameworkException ex) {
				ex.printStackTrace();
			}
		}	
		return handlesName;
	}
	
	public HashMap<String, String> getMapOfHandleAndInformation(List<String> lstOfHandles){
		HashMap<String, String> hMapInfo = new HashMap<>();
		String strHover = "//span[text() = '%s']";
		String strOverAllInfo, strFollowingCount, strFollowerCount, strBio;
		try{
			for(String handle: lstOfHandles){
				pageWebDriverClient.hover(strHover.replace("%s", handle));
				pageWebDriverClient.waitForVisibilityOfElementLocatedBy(strFollowLink);
				strFollowingCount = pageWebDriverClient.getText(lbl_FollowingCount);
				strFollowerCount = pageWebDriverClient.getText(lbl_FollowerCount);
				strBio = pageWebDriverClient.getText(strBioText.replace("%s", handle));
				strOverAllInfo = strFollowingCount + ", " + strFollowerCount + ", " + strBio;
				hMapInfo.put(handle, strOverAllInfo);
			}			
			//key - value map where key is handleName and value is "followingCount, followerCount, BioText"
		}catch(Exception e){
			try {
				throw new TestFrameworkException("Unable to fetch list", e);
			} catch (TestFrameworkException ex) {
				ex.printStackTrace();
			}
		}	
		return hMapInfo;
	}
	

}
