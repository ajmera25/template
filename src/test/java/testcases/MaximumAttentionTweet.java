package testcases;

import core.BaseTest;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import pageobjects.desktop.LoginPage;
import pageobjects.desktop.ProfilePage;
import pageobjects.desktop.SearchPage;
import pageobjects.desktop.TweetPage;

public class MaximumAttentionTweet extends BaseTest {

    @Test
    public void test001_getTestTweet() throws Exception{
      Assert.assertTrue(new LoginPage(driver).doLogin("", ""), "Login failed !!");
      Assert.assertTrue(new SearchPage(driver).searchTweet("@stepin_forum"), "Failed to search");
    }
    
    @Test(dependsOnMethods = "test001_getTestTweet")
    public void test002_selectTweetWithMaxTweetsAndLikes() throws Exception{
    	Assert.assertTrue(new ProfilePage(driver).clickTweet(), "Failed to click on tweet with max retweets and likes.");
    }

    @Test(dependsOnMethods = "test002_selectTweetWithMaxTweetsAndLikes")
    public void test003_getInformationOfTweet() throws Exception{
    	HashMap<String, List<String>> tweetLiked = new HashMap<>();
    	HashMap<String, List<String>> tweetReTweeted = new HashMap<>();
    	TweetPage tweetPage = new TweetPage(driver);
    	tweetLiked = tweetPage.getMapOfHandleAndInformationWhoLiked(tweetPage.getListOfHandleOfUsersWhoLiked());
    	tweetReTweeted = tweetPage.getMapOfHandleAndInformationWhoReTweeted(tweetPage.getListOfHandleOfUsersWhoRetweeted());
    	
    	HashMap<String, List<String>> tweetInfo = new HashMap<>(tweetReTweeted);
    	tweetInfo.putAll(tweetLiked);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tweetInfo);
    	String filePath = System.getProperty("user.dir") + "/src/test/resources/results/WebTwitterDetails.js";
    	try (FileWriter file = new FileWriter(filePath)){
			file.write("var data="+jsonData);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
    	Assert.assertTrue(new File(filePath).exists());
    }
    
}
