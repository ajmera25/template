package testcases;

import core.BaseTest;

import java.util.HashMap;

import org.testng.annotations.Test;
import pageobjects.desktop.LoginPage;
import pageobjects.desktop.ProfilePage;
import pageobjects.desktop.SearchPage;
import pageobjects.desktop.TweetPage;
import utilities.TestFrameworkException;

public class MaximumAttentionTweet extends BaseTest {

    @Test
    public void test001_getTestTweet() throws TestFrameworkException{
      new LoginPage(driver).doLogin("SurnameTooLong", "bharatdemo");
      new SearchPage(driver).searchTweet("@stepin_forum");
    }
    
    @Test   
    public void test002_selectTweet() throws Exception{
        //open tweet with maximum attention
        new ProfilePage(driver).clickTweet();
    }

    @Test
    public void test003_getInformationOfTweet() throws TestFrameworkException{
    	HashMap<String, String> hMapInfoLiked = new HashMap<>();
    	HashMap<String, String> hMapInfoReTweeted = new HashMap<>();
    	hMapInfoLiked = new TweetPage(driver).getMapOfHandleAndInformationWhoLiked
    			(new TweetPage(driver).getListOfHandleOfUsersWhoLiked());
    	hMapInfoReTweeted = new TweetPage(driver).getMapOfHandleAndInformationWhoReTweeted
    			(new TweetPage(driver).getListOfHandleOfUsersWhoRetweeted());
    	
    	System.out.println(hMapInfoLiked);
    	System.out.println(hMapInfoReTweeted);
    }
    
}
