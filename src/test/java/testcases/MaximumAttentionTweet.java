package testcases;

import core.BaseTest;
import org.testng.annotations.Test;
import pageobjects.desktop.LoginPage;

public class MaximumAttentionTweet extends BaseTest {

    @Test
    public void getTestTweet(){
      new LoginPage(driver).doLogin("SurnameTooLong","bharatdemo");
    }
}
