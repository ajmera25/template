package pageobjects.desktop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;

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
		
		
	public List<String> getListOfHandleOfUsersWhoLiked(){
		List<String> handlesName = new ArrayList<String>();
		try{
			pageWebDriverClient.click(lnk_Likes);
			pageWebDriverClient.waitForVisibilityOfElementLocatedBy(strUsersCell);
			handlesName = getListOfHandleOfUsers();
		}catch(Exception ex){
			ex.printStackTrace();
	}	
		return handlesName ;
	}
	
	public List<String> getListOfHandleOfUsersWhoRetweeted(){
		List<String> handlesName = new ArrayList<String>();
		try{
			pageWebDriverClient.click(lnk_Retweets);
			pageWebDriverClient.waitForVisibilityOfElementLocatedBy(strUsersCell);
			handlesName = getListOfHandleOfUsers();
		}catch(Exception ex){
				ex.printStackTrace();
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
		}catch(Exception ex){
			ex.printStackTrace();
	}	
		return handlesName;
	}
	
	public HashMap<String, List<String>> getMapOfHandleAndInformationWhoLiked(List<String> lstOfHandles){
		HashMap<String, List<String>> hMapInfo = new HashMap<>();
		try{
			//pageWebDriverClient.click(lnk_Likes);
			pageWebDriverClient.waitForVisibilityOfElementLocatedBy(strUsersCell);
			hMapInfo = getMapOfHandleAndInformation(lstOfHandles);
		}catch(Exception ex){
			ex.printStackTrace();
	}	
		return hMapInfo ;
	}
	
	public HashMap<String, List<String>> getMapOfHandleAndInformationWhoReTweeted(List<String> lstOfHandles){
		HashMap<String, List<String>> hMapInfo = new HashMap<>();
		try{
			//pageWebDriverClient.click(lnk_Retweets);
			pageWebDriverClient.waitForVisibilityOfElementLocatedBy(strUsersCell);
			hMapInfo = getMapOfHandleAndInformation(lstOfHandles);
		}catch(Exception ex){
			ex.printStackTrace();
	}	
		return hMapInfo ;
	}
	
	public HashMap<String, List<String>> getMapOfHandleAndInformation(List<String> lstOfHandles){
		HashMap<String, List<String>> hMapInfo = new HashMap<>();
		String strHover = "//span[text() = '%s']";
		String strBioText = "//span[text()='%s']/ancestor::a/parent::div/following-sibling::div/div[@dir='auto']/span[text()]";
		String bioText = "";
		try{
			//key - value map where key is handleName and value is "followingCount, followerCount, BioText"
			for(String handle: lstOfHandles){
				pageWebDriverClient.hover(strHover.replace("%s", handle));
				pageWebDriverClient.waitForVisibilityOfElementLocatedBy(strFollowLink);
				List<String> handleInfo = new ArrayList<>();
				handleInfo.add(pageWebDriverClient.getText(lbl_FollowingCount));
				handleInfo.add(pageWebDriverClient.getText(lbl_FollowerCount));
				if(pageWebDriverClient.findElements(strBioText.replace("%s", handle)).size() > 0) {
					bioText = pageWebDriverClient.getText(strBioText.replace("%s", handle));
				}else {
					bioText = "";
				}
				handleInfo.add(bioText);
				handleInfo.add(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64));
				hMapInfo.put(handle, handleInfo);
				//To remove hover
				pageWebDriverClient.click("//div[@aria-label='Close']/parent::div/following-sibling::div");
			}
			pageWebDriverClient.click("//div[@aria-label='Close']");
			
		}catch(Exception ex){
			ex.printStackTrace();
	}	
		return hMapInfo;
	}
	

}
